package com.diploma.ticket.system.controller;

import com.diploma.ticket.system.entity.TicketType;
import com.diploma.ticket.system.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(path="api/v2/tickettype")
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    @Autowired
    public TicketTypeController(TicketTypeService ticketTypeService){
        this.ticketTypeService=ticketTypeService;
    }

    @GetMapping
    public ResponseEntity<List<TicketType>> getTickets(){
        List<TicketType> responseBody=ticketTypeService.getTicketTypes();
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<TicketType> registerNewTicket(
            @RequestBody TicketType ticket
    ){
        TicketType responseBody= ticketTypeService.addNewTicket(ticket);

        return ResponseEntity.created(URI.create("TicketType"))
                .body(responseBody);
    }
    @PatchMapping(path="{ticketTypeName}")
    public ResponseEntity<String> updateTicket(
            @PathVariable("ticketTypeName")String name,
            @RequestBody TicketType ticket)
    {
        ticketTypeService.updateTicket(name, ticket);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicketType(
            @PathVariable Long id
    ) {
        ticketTypeService.deleteTicketType(id);
        return ResponseEntity.ok("Ticket type whit id:"+id+" was deleted successfully!");
    }
}
