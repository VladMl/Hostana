package com.vladml.hostana.util.ansible;

import java.util.HashMap;

interface AnsibleInventoryLoader {
    public Object load(String name) throws Exception;
}

public class AnsibleInventoryManager {
    private HashMap<String, AnsibleInventoryLoader> loaders = new HashMap<String, AnsibleInventoryLoader>();

    public void addLoader(AnsibleInventoryLoader loader, String extension) {
        loaders.put(extension, loader);
    }

    public <T> T load(String name) {
        int i = name.lastIndexOf('.');
        if (i == -1)
            throw new RuntimeException("\"" + name + "\" has no extension, and so has no associated asset loader");

        String extension = name.substring(i + 1);
        AnsibleInventoryLoader loader = loaders.get(extension);
        if (loader == null)
            throw new RuntimeException("No loader registered for \"." + extension + "\" files");
        try {
            return (T) loader.load(name);
        } catch (ClassCastException e) {
            throw new RuntimeException("\"" + name + "\" could not be loaded as the expected type");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + name, e);
        }
    }
}