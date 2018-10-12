package com.vladml.hostana.util.ansible;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AnsibleInventoryReader {

    public static void readFile(String fileName) {
        List<String> ini = new ArrayList<String>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            ini = lines
                    .filter(s -> s.trim().length() > 0)
                    .collect(Collectors.toList());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
