package com.vladml.hostana.model.dto;

import com.vladml.hostana.model.Label;
import lombok.Data;

@Data
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

    private String softwareView;

    private String guiView;

}
