package com.diploma.ticket.system.service;

import com.diploma.ticket.system.payload.request.TicketCreationRequest;
import com.diploma.ticket.system.payload.response.TicketCreationResponse;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.repository.FavorRepository;
import com.diploma.ticket.system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FavorRepository favorRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, FavorRepository favorRepository){
        this.ticketRepository=ticketRepository;
        this.favorRepository=favorRepository;
    }
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public TicketCreationResponse addNewTicket(TicketCreationRequest request) {
        Optional<Ticket> ticketOptional
                =ticketRepository.findByTicketName(request.getName());
        boolean exists=ticketOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }

        Favor favor
                =favorRepository.getReferenceById(request.getFavorId());
        Set<Favor> favors=new HashSet<>();
        favors.add(favor);

        Ticket createdTicke=
                 Ticket.builder()
                         .name(request.getName())
                         .workStart(request.getWorkStart())
                         .workEnd(request.getWorkEnd())
                         .favors(favors)
                         .build();

        ticketRepository.save(createdTicke);
        Long idOfTheNewTicket =createdTicke.getId();
        return new TicketCreationResponse(idOfTheNewTicket,"Ticket Created successfully");
    }

    public void updateTicket(String nameOfTicketToUpdate, Ticket ticket) {
        String name=ticket.getName();
        Optional<Ticket> updatedTicket=ticketRepository.findByTicketName(nameOfTicketToUpdate);
        boolean exits=updatedTicket.isPresent();
        if(exits) {
            new IllegalStateException("ticket whit name " + nameOfTicketToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(ticket.getName(),name)){
            updatedTicket.get().setName(name);
        }
        ticketRepository.save(ticket);
    }
}
