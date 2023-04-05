package com.diploma.TicketSystem.Ticketing.TicketType;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import com.diploma.TicketSystem.Ticketing.Ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="api/v1/tickettype")
public class TicketTypeControler {

    private final TicketStypeService ticketTypeService;

    @Autowired
    public TicketTypeControler(TicketStypeService ticketTypeService){
        this.ticketTypeService=ticketTypeService;
    }

    @GetMapping
    public List<TicketType> getTickets(){

        return ticketTypeService.getTicketTypes();
    }

    @PostMapping
    public void registerNewTicket(@RequestBody TicketType ticket){
        ticketTypeService.addNewTicket(ticket);
    }

    @PutMapping(path="{ticketTypeId}")
    public void updateTicket(@PathVariable("ticketTypeName")String name,
                             @RequestBody TicketType ticket){
        ticketTypeService.updateTicket(name,ticket);
    }
}
