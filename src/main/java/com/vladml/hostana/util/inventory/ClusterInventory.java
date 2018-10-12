package com.vladml.hostana.util.inventory;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class ClusterInventory extends LinkedHashMap {

    private String name;


    public ClusterInventory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addHost(String groupName, HostInventory hostInventory) {
        if (this.get(groupName) == null) {
            ArrayList<HostInventory> hostGroup = new ArrayList<HostInventory>();
            hostGroup.add(hostInventory);
            this.put(groupName, hostGroup);
        }
        else {
            ArrayList<HostInventory> host = (ArrayList<HostInventory>) this.get(groupName);
            host.add(hostInventory);
        }

    }

}
