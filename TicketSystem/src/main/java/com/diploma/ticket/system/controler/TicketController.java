package com.diploma.ticket.system.controler;
import com.diploma.ticket.system.payload.request.TicketCreationRequest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.TicketService;
import com.diploma.ticket.system.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/v2/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(){
        List<Ticket> responceBody=ticketService.getTickets();
        return ResponseEntity.ok().body(responceBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewTicket(
            @RequestBody TicketCreationRequest ticket
    ){
        CreationResponse responseBody=ticketService.addNewTicket(ticket);
        return ResponseEntity.created(URI.create("Ticket")).body(responseBody);
    }

    @PatchMapping(path="{ticketId}")
    public void updateTicket(@PathVariable("ticketName")String name,
                             @RequestBody Ticket ticket){
        ticketService.updateTicket(name,ticket);
    }

    @PutMapping("addFavor/{idTicket}/{idFavor}")
    public void addFavor(
            @PathVariable("idTicket") Long idTicket,
            @PathVariable("idFavor") Long idFavor
    ){
        ticketService.addFavor(idTicket,idFavor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteATicket(
            @PathVariable Long id
    ) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }

}
