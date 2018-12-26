package com.vladml.hostana.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "hosts")
@Data
@Builder
public class Host implements Cloneable {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "hostname")
    private String hostName;

    @Column(name = "group_name")
    private String groupName;


    private String clusterName;

    private String os;

    private String hardware;

    private String gui;

    private String software;

    private String softwareJson;

    private String updateStatus;

    private String lastUpdate;

    private String updateLogUrl;

    private String description;

    private Integer sortOrder;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "label_id", nullable = false)
    private Label label;


    public HashMap getFieldMap() {
        HashMap<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("hostName", this.getHostName());
        fieldMap.put("groupName", this.getGroupName());
        return fieldMap;
    }

}
