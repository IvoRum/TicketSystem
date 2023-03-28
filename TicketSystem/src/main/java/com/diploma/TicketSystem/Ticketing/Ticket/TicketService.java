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
        if(ticketOptional.isPresent()){
            throw new IllegalStateException("Number is taken");
        }
        ticketRepository.save(ticket);
    }

    public void updateTicket(Long ticketId, Ticket ticket) {
//    private Long id;
//    private String name;//?
//    private Long number;
//    private TicketType type;
        Long numbet=ticket.getNumber();
        Ticket updatedTicket=ticketRepository.findById(ticketId).orElseThrow(()->
                new IllegalStateException("ticket whit id "+ ticketId+" or does not exost")
        );
        if(numbet!=null
                &&!Objects.equals(ticket.getNumber(),numbet)){
            updatedTicket.setNumber(numbet);
        }
    }
}
