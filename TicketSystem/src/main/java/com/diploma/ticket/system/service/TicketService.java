package com.diploma.ticket.system.service;

import com.diploma.ticket.system.dto.TicketCreationRequest;
import com.diploma.ticket.system.dto.TicketCreationResponse;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public TicketCreationResponse addNewTicket(TicketCreationRequest request) {
        Optional<Ticket> ticketOptional
                =ticketRepository.findByTicketName(request.getName());
        boolean exists=ticketOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }
        Ticket createdTicke=
                 Ticket.builder()
                         .name(request.getName())
                         .workStart(request.getWorkStart())
                         .workEnd(request.getWorkEnd())
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
