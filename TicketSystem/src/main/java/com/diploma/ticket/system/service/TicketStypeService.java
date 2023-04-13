package com.diploma.ticket.system.service;

import com.diploma.ticket.system.repository.TicketTypeRepository;
import com.diploma.ticket.system.entity.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class TicketStypeService {
    private final TicketTypeRepository ticketRepository;
    @Autowired
    public TicketStypeService(TicketTypeRepository ticketTypeRepository){this.ticketRepository=ticketTypeRepository;}
    public List<TicketType> getTicketTypes() {
        return ticketRepository.findAll();
    }

    public void updateTicket(String nameOfTicketTypeTochainge, TicketType ticket) {
        String name=ticket.getName();
        Optional<TicketType> updatedTicket=ticketRepository.findByTicketTypesName(nameOfTicketTypeTochainge);
        boolean exits=updatedTicket.isPresent();
        if(exits) {
            new IllegalStateException("ticket whit name " + nameOfTicketTypeTochainge + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(ticket.getName(),name)){
            updatedTicket.get().setName(name);
        }
        ticketRepository.save(ticket);
    }

    public void addNewTicket(TicketType ticketType) {
        Optional<TicketType> ticketOptional
                =ticketRepository.findByTicketTypesName(ticketType.getName());
        boolean exists=ticketOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }
        ticketRepository.save(ticketType);
    }
}
