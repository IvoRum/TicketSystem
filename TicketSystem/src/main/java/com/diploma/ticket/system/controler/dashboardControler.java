package com.diploma.ticket.system.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dashboardControler {

    @GetMapping("/")
    public String home(){
        return "home.html";
    }
}
