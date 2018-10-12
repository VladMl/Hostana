package com.vladml.hostana.util.inventory;

public class HostInventory {

    private String hostName;

    public String getHostname() {
        return this.hostName;
    }

    public void setHostname(String hostName) {
        this.hostName = hostName;
    }

    public HostInventory(String hostName) {
        this.hostName = hostName;
    }

}
