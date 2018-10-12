package com.vladml.hostana.util.templates;

import groovy.util.Eval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Templates extends ArrayList<String>{

    public boolean addTemplates(List<String> list) {
        this.clear();
        return this.addAll(list);
    };



    private String evalGuiTemplate(String template, HashMap<String, String> values) {
        String template_ = template;
        try {
            template_ = values.keySet().stream()
                    .reduce(template, (acc, e) ->acc.replaceAll("\\$\\{" + e + "\\}", (values.get(e)==null) ? "null" : (String) values.get(e))
                    );
        } catch (Exception e) {
            System.out.println("ERROR in template : "+template_);
            values.forEach((key, value) ->
                    {System.out.println(key+" : "+value);}
                    );

            e.printStackTrace();
        }
        return (String) Eval.me(template_);
    }


    public String getValueFromTemplate(String template, HashMap values) {
        return evalGuiTemplate(template, values);
    }

    public String getValueFromTemplateForAll(HashMap values) {
        String returnValue = "";
        String newValue = "";
        for (String template : this) {
            newValue = evalGuiTemplate(template, values);
            returnValue += (newValue == null || newValue.equals("null")) ? "" : newValue;
        }
        return returnValue;
    }


}
