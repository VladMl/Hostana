package com.vladml.hostana.host;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladml.hostana.util.Config;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class HostMapper extends ModelMapper {

    final static String updateStatusSuccess = "webix_icon icon_green fa-check-circle";
    final static String updateStatusError = "webix_icon icon_red fa-times-circle";
    final static String updateStatusIcon = "<a target=\"blank\" href=\"%s\"><span class=\"%s\"></span></a>";


    @Autowired
    Config appConfig;

    private HashMap<String, String> filedMap;


    private String getGui(String value) {
        if (!StringUtils.isEmpty(value))
            return appConfig.getGuiTemplates().getValueFromTemplate(value, this.filedMap);
        else
            return appConfig.getGuiTemplates().getValueFromTemplateForAll(this.filedMap);
    }


    private String getSoftware(String value, String json) {
        if (!StringUtils.isEmpty(json)) {


            HashMap<String, String> jsonValues = null;
            try {
                jsonValues = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, String>>() {
                });
                this.filedMap.putAll(jsonValues);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        if (!StringUtils.isEmpty(value))
            return appConfig.getSoftwareTemplates().getValueFromTemplate(value, this.filedMap);
        else
            return appConfig.getSoftwareTemplates().getValueFromTemplateForAll(this.filedMap);
    }


    private String getUpdateStatus(String value, String updateLogUrl) {
        if (value != null)
            if (value.toLowerCase().equals("success"))
                return String.format(updateStatusIcon, updateLogUrl, updateStatusSuccess);
            else
                return String.format(updateStatusIcon, updateLogUrl, updateStatusError);
        return value;
    }


    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        Object tmpSource = source;
        if (source == null) {
            tmpSource = new Object();
        }

        Host host = (Host) source;
        this.filedMap = host.getFieldMap();

        HostDto hostDto = (HostDto) super.map(tmpSource, destinationType);

        //hostDto.setGui(this.getGui(((Host) tmpSource).getGui()));
        //hostDto.setSoftware(this.getSoftware(((Host) tmpSource).getSoftware(), ((Host) tmpSource).getSoftwareJson()));

        hostDto.setGuiView(this.getGui(((Host) tmpSource).getGui()));
        hostDto.setSoftwareView(this.getSoftware(((Host) tmpSource).getSoftware(), ((Host) tmpSource).getSoftwareJson()));

        hostDto.setUpdateStatus(this.getUpdateStatus(((Host) tmpSource).getUpdateStatus(), ((Host) tmpSource).getUpdateLogUrl()));

        return (D) hostDto;
    }

}
