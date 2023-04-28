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
        List<Ticket> responceBody=new ArrayList<>();
        try{
            responceBody=ticketService.getTickets();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responceBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewTicket(
            @RequestBody TicketCreationRequest ticket
    ){
        CreationResponse responseBody=null;
        try {
            responseBody=
                    ticketService.addNewTicket(ticket);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("Ticket")).body(responseBody);
    }

    @PatchMapping(path="{ticketId}")
    public void updateTicket(@PathVariable("ticketName")String name,
                             @RequestBody Ticket ticket){
        ticketService.updateTicket(name,ticket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteATicket(
            @PathVariable Long id
    ) {
        try{
            ticketService.deleteTicket(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
