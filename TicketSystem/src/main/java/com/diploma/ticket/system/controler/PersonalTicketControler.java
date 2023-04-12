package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.service.PersonalTicketService;
import com.diploma.ticket.system.entity.PersonalTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/personalticket")
public class PersonalTicketControler {
    private final PersonalTicketService personalTicketService;

    @Autowired
    public PersonalTicketControler(PersonalTicketService personalTicketService){
        this.personalTicketService=personalTicketService;
    }
    @GetMapping
    public List<PersonalTicket> getTickets(){
        return personalTicketService.getPersonaTickets();
    }

    @PutMapping(path="{personalTicketId}")
    public void updateTicket(@PathVariable("ticketNumber")Long number,
                             @RequestBody PersonalTicket personalTicket){
        personalTicketService.updatePersonalTicket(number,personalTicket);
    }

    @PostMapping
    public void registerPersonalTicket(@RequestBody PersonalTicket personalTicket){
        personalTicketService.addNewPersonalTicket(personalTicket);
    }
}
