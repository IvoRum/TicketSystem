package com.diploma.ticket.system.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class QueueController {

    @PostMapping
    public void createNewCounter(){}

    @PutMapping
    public void finishedTicket(){}

    @GetMapping
    public void getNextInLine(){}

    @PutMapping
    public void callNextInLine(){}

}
