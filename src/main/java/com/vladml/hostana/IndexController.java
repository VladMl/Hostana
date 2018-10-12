package com.vladml.hostana;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@PropertySource("/config.yml")
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return  "index.html";
    }

}