package com.diploma.ticket.system.controler;
import com.diploma.ticket.system.dto.TicketCreationRequest;
import com.diploma.ticket.system.dto.TicketCreationResponse;
import com.diploma.ticket.system.service.TicketService;
import com.diploma.ticket.system.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(){
        return ResponseEntity.ok().body(ticketService.getTickets());
    }

    @PostMapping
    public ResponseEntity<TicketCreationResponse> registerNewTicket(
            @RequestBody TicketCreationRequest ticket
    ){
        TicketCreationResponse responseBody=null;
        try {
            responseBody=
                    ticketService.addNewTicket(ticket);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping(path="{ticketId}")
    public void updateTicket(@PathVariable("ticketName")String name,
                             @RequestBody Ticket ticket){
        ticketService.updateTicket(name,ticket);
    }
}
