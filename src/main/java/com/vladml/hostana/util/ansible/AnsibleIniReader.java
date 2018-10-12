package com.vladml.hostana.util.ansible;


import com.vladml.hostana.util.inventory.ClusterInventory;
import com.vladml.hostana.util.inventory.HostInventory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AnsibleIniReader implements AnsibleReader, AnsibleInventoryLoader {


    final static char grBracket = '[';

    @Override
    public LinkedHashMap load(String fileName) {
        ClusterInventory clusterInventory = new ClusterInventory(fileName);
        ArrayList<HostInventory> hosts;
        String groupName = "";

        List<String> ini = new ArrayList<String>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            ini = lines
                    .filter(s -> s.trim().length() > 0)
                    .collect(Collectors.toList());
        } catch (IOException io) {
            io.printStackTrace();
        }

        for (String line : ini) {
            if (line.length() == 0) continue;
            if (line.charAt(0) == grBracket) {
                groupName = line.replaceAll("[\\[\\]]", "");
                continue;
            }

            clusterInventory.addHost(groupName, new HostInventory(line));
        }

        return clusterInventory;
    }
}
