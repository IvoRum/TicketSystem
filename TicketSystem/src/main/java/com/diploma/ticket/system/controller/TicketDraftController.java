package com.diploma.ticket.system.controller;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.FavorService;
import com.diploma.ticket.system.service.PersonalTicketService;
import com.diploma.ticket.system.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="api/v1/draft")
public class TicketDraftController {
    private final QueueService queueService;
    private final PersonalTicketService personalTicketService;
    private final TicketController ticketService;
    private final FavorService favorService;


    @Autowired
    public TicketDraftController(
            QueueService queueService,
            PersonalTicketService personalTicketService,
            TicketController ticketService,
            FavorService favorService) {
        this.queueService = queueService;
        this.personalTicketService = personalTicketService;
        this.ticketService = ticketService;
        this.favorService = favorService;
    }

    @GetMapping("/favor")
    public ResponseEntity<List<Favor>> getService(){
        List<Favor> responseBody=new ArrayList<>();
        responseBody=favorService.getService();

        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping("/waitingForCounter/{counterId}")
    public Set<PersonalTicket> getWaitingForCounter(
            @PathVariable Long counterId
    ){
        return queueService.getWaithingForCounter(counterId);
    }

    @GetMapping("/lastPersonalTicket")
    public Long getLastPersonalTicket(){
        return personalTicketService.getLastPersonTicketId();
    }

    @PostMapping("/{ticketId}")
    public ResponseEntity<CreationResponse> draftTicket(
            @PathVariable Long ticketId,
            @RequestBody PersonalTicket personalTicket
    ){
        PersonalTicket draftedPersonalTicket
                =personalTicketService.addNewPersonalTicket(personalTicket);

        ticketService.addPersonalTicket(ticketId,personalTicket.getId());

        CreationResponse response=
                new CreationResponse(
                        personalTicket.getId(),
                        "Personal Ticket for Ticket whit id:"
                                +ticketId+" has bean created successfully");
        return ResponseEntity.created(URI.create("PersonaTicket")).body(response);
    }
}
