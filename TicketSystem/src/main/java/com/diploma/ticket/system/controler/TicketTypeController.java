package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.TicketType;
import com.diploma.ticket.system.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
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
        List<TicketType> responseBody=new ArrayList<>();
        try{
            responseBody=ticketTypeService.getTicketTypes();
        }catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<TicketType> registerNewTicket(@RequestBody TicketType ticket){
        TicketType responseBody=null;
        try {
            responseBody=
            ticketTypeService.addNewTicket(ticket);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("TickeType"))
                .body(responseBody);
    }

    @PutMapping(path="{ticketTypeId}")
    public void updateTicket(@PathVariable("ticketTypeName")String name,
                             @RequestBody TicketType ticket){
        ticketTypeService.updateTicket(name,ticket);
    }
}
