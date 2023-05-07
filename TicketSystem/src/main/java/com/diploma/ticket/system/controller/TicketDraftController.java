package com.diploma.ticket.system.controller;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.PersonalTicketService;
import com.diploma.ticket.system.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path="api/v1/draft")
public class TicketDraftController {
    private final QueueService queueService;
    private final PersonalTicketService personalTicketService;
    private final TicketController ticketService;

    @Autowired
    public TicketDraftController(
            QueueService queueService,
            PersonalTicketService personalTicketService,
            TicketController ticketService
    ) {
        this.queueService = queueService;
        this.personalTicketService = personalTicketService;
        this.ticketService = ticketService;
    }

    @GetMapping("/waitingForCounter/{counterId}")
    public Set<PersonalTicket> getWaitingForCounter(
            @PathVariable Long counterId
    ){
        return queueService.getWaithingForCounter(counterId);
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
