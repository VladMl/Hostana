package com.vladml.hostana.labels;


import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LabelController {

    @Logger
    static org.slf4j.Logger LOGGER;

    @Autowired
    LabelRepository labelRepository;

    @GetMapping("/labels")
    public List<Label> getLabels() {
        return labelRepository.findAllByOrderByNameAsc();
    }


    @PostMapping("/label")
    public ResponseEntity<?> labelPost(@RequestParam Map<String, String> body) {
        try {
            Label label = new Label();
            label.setName(body.get("name"));
            label.setColor(body.get("color"));
            labelRepository.save(label);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/label/{id}")
    public ResponseEntity labelDelete(@PathVariable("id") long id) {
        labelRepository.deleteById(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping("/label/{id}")
    public ResponseEntity labelUpdate(@PathVariable("id") long id, @RequestParam Map<String, String> body) {
        Label label = labelRepository.findById(id)
                .orElse(null);
        if (label != null) {
            label.setName(body.get("name"));
            label.setColor(body.get("color"));
            labelRepository.save(label);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(id, HttpStatus.OK);
    }

}
