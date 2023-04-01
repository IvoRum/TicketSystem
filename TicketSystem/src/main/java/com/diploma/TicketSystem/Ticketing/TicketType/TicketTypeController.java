package com.diploma.TicketSystem.Ticketing.TicketType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="api/v1/ticket_type")
public class TicketTypeController {

        private final TicketTypeService ticketTypeService;

        @Autowired
        public TicketTypeController(TicketTypeService ticketTypeService){
            this.ticketTypeService=ticketTypeService;
        }

        @GetMapping
        public List<TicketType> getTickets(){

            return ticketTypeService.getTicketType();
        }

        @PostMapping
        public void registerNewTicket(@RequestBody TicketType ticket){
            ticketTypeService.addNewTicketType(ticket);
        }

        @PutMapping(path="{ticketId}")
        public void updateTicket(@PathVariable("ticketTypeId")Long id,
                                 @RequestBody TicketType ticket){
            ticketTypeService.updateTicketType(id,ticket);
        }
}
