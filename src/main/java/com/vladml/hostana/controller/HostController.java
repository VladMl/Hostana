package com.vladml.hostana.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladml.hostana.model.Label;
import com.vladml.hostana.model.dto.HostMapper;
import com.vladml.hostana.repository.LabelRepository;
import com.vladml.hostana.model.Host;
import com.vladml.hostana.model.dto.HostDto;
import com.vladml.hostana.repository.HostRepository;
import com.vladml.hostana.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HostController {

    @Autowired
    final HostRepository hostRepository;

    @Autowired
    final LabelRepository labelRepository;


    @Autowired
    private HostMapper modelMapper;

    @Autowired
    private Config appConf;


    public HostController(HostRepository hostRepository, LabelRepository labelRepository) {
        this.hostRepository = hostRepository;
        this.labelRepository = labelRepository;
    }


    private HostDto convertToDto(Host host) {
        HostDto hostDto = modelMapper.map(host, HostDto.class);
        return hostDto;
    }

    @GetMapping("/dashboard/hosts")
    public List<HostDto> getDashboardHosts() {

        appConf.load();

        List<Host> hosts = hostRepository.findAllByOrderBySortOrder();

        return hosts.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    @GetMapping("/hosts")
    public List<Host> getHosts() {

        appConf.load();

        List<Host> hosts = hostRepository.findAllByOrderBySortOrder();

        return hosts;
    }


    @GetMapping("/cfg")
    public void reloadConfig() {
        appConf.load();
    }

    @RequestMapping(value = "/host_info", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> hostInfoSubmit(@RequestBody String json) {


        ObjectMapper mapper = new ObjectMapper();

        try {

            HashMap<String, String> values = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, Object>>() {
            });

            Host host = hostRepository.findByHostName(values.get("hostName"));

            values.putAll(host.getFieldMap());


            host.setUpdateStatus(values.get("updateStatus"));

            host.setLastUpdate(new SimpleDateFormat("dd.MM.YYYY HH.mm.ss").format(new Date()));
            host.setUpdateLogUrl(values.get("updateLogUrl"));
            host.setSoftwareJson(json);

            hostRepository.save(host);
            return new ResponseEntity(HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



    @PutMapping("/host/{id}")
    public ResponseEntity hostUpdate(@PathVariable("id") long id, @RequestParam Map<String, String> body) {
        Host host = hostRepository.findById(id)
                .orElse(null);
        if (host != null) {
            Label label = null;
            if (!body.get("label").equals(""))
                label = labelRepository.findById(Long.parseLong(body.get("label")))
                        .orElse(null);

/*
            host.setHardware(body.get("hardware"));
            host.setOs(body.get("os"));
            host.setDescription(body.get("description"));
            host.setLabel(label);
            */
            hostRepository.save(host);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }


}
