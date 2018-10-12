package com.vladml.hostana.host;

import com.vladml.hostana.labels.Label;


public class HostDto {

    public Long id;

    private String hostName;

    private String groupName;


    private String clusterName;

    private String os;

    private String hardware;

    private String gui;

    private String software;

    private String updateStatus;

    private String lastUpdate;

    private String updateLogUrl;

    private String description;

    private Label label;

    private Integer sortOrder;

    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }


    public String getGui() {
        return gui;
    }

    public void setGui(String gui) {
        this.gui = gui;
    }


    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }


    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUpdateLogUrl() {
        return updateLogUrl;
    }

    public void setUpdateLogUrl(String updateLogUrl) {
        this.updateLogUrl = updateLogUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Label getlabel() {
        return label;
    }

    public void setlabel(Label label) {
        this.label = label;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
