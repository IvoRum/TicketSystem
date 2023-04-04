package com.diploma.TicketSystem.Ticketing.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public void addNewTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional
                =ticketRepository.findByTicketNumber(ticket.getNumber());
        boolean exists=ticketOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Number is taken");
        }
        ticketRepository.save(ticket);
    }

    public void updateTicket(Long numberOfTicketToUpdate, Ticket ticket) {
        Long numbet=ticket.getNumber();
        Optional<Ticket> updatedTicket=ticketRepository.findByTicketNumber(numberOfTicketToUpdate);
        boolean exits=updatedTicket.isPresent();
        if(exits) {
            new IllegalStateException("ticket whit number " + numberOfTicketToUpdate + " does not exost");
        }
        if(numbet!=null
                &&!Objects.equals(ticket.getNumber(),numbet)){
            updatedTicket.get().setNumber(numbet);
        }
        ticketRepository.save(ticket);
    }
}
