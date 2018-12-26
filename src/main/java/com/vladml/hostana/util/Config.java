package com.vladml.hostana.util;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.vladml.hostana.HostanaApp;
import com.vladml.hostana.model.Host;
import com.vladml.hostana.repository.HostRepository;
import com.vladml.hostana.util.ansible.AnsibleIniReader;
import com.vladml.hostana.util.ansible.AnsibleInventoryManager;
import com.vladml.hostana.util.inventory.HostInventory;
import com.vladml.hostana.util.templates.GuiTemplates;
import com.vladml.hostana.util.templates.SoftwareTemplates;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import org.yaml.snakeyaml.Yaml;

@Component
@Configuration
public class Config {


    @Autowired
    HostRepository hostRepository;

    @Autowired
    GuiTemplates guiTemplates;

    @Autowired
    SoftwareTemplates softwareTemplates;

    @Value("${application.config}")
    private String appConfig;

    private AnsibleInventoryManager inventoryManager;

    private AtomicInteger hostCount;



    private static boolean isDirectory(String path) {
        return new File(path).isDirectory();
    }

    private void loadInventoryFile(String path) {
        /*
        LinkedHashMap inventory = inventoryManager.load(path);
        inventory.forEach((groupName, hosts)->{
            ArrayList<HostInventory> hostList = (ArrayList<HostInventory>) hosts;
            hostList.forEach((host)->{
                Host hostDB = hostRepository.findByHostName(host.getHostname());
                if (hostDB == null)
                    hostDB = Host.builder().build();

                hostDB.setHostName(host.getHostname());
                hostDB.setGroupName((String) groupName);
                hostDB.setSortOrder(hostCount.getAndIncrement());
                hostDB.setClusterName(FilenameUtils.getBaseName((String) path));
                hostRepository.save(hostDB);

            });
        });
        */
    }

    private void loadInventory(ArrayList inventory) {
        hostCount.set(0);
        inventory.forEach((pathInventory) -> {
            String path = (String )pathInventory;
                    if (isDirectory(path)) {
                        try {
                            Files.list(Paths.get(path))
                                    .forEach((filename) -> {
                                        loadInventoryFile(filename.toString());
                                    });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else
                        loadInventoryFile(path);
                });

    }


    public void load() {

        Yaml yaml = new Yaml();

        InputStream input = null;
        try {
           ApplicationHome home = new ApplicationHome(HostanaApp.class);
           if (StringUtils.isEmpty(appConfig))
              input = new FileInputStream(new File(home.getDir()+"/config.yml"));
           else
               input = new FileInputStream(new File(appConfig));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        LinkedHashMap lobj = yaml.load(input);

        Object inventories = lobj.get("inventory");
        loadInventory((ArrayList) inventories);

        Object guiTempl = lobj.get("gui-templates");
        guiTemplates.addTemplates((List<String>)guiTempl);

        Object softwareTempl = lobj.get("hostinfo-templates");
        softwareTemplates.addTemplates((List<String>)softwareTempl);

    }

    public GuiTemplates getGuiTemplates() {
        return this.guiTemplates;
    }

    public SoftwareTemplates getSoftwareTemplates() {
        return this.softwareTemplates;
    }

    public Config() {
        inventoryManager = new AnsibleInventoryManager();
        inventoryManager.addLoader(new AnsibleIniReader(), "ini");
        hostCount = new AtomicInteger();
    }

}