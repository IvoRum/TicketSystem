package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.*;
import com.diploma.ticket.system.payload.request.TicketCreationRequest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FavorRepository favorRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final PersonalTicketRepository personalTicketRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketService(
            TicketRepository ticketRepository,
            FavorRepository favorRepository,
            TicketTypeRepository ticketTypeRepository,
            PersonalTicketRepository personalTicketRepository, UserRepository userRepository
    ){
        this.ticketRepository=ticketRepository;
        this.favorRepository=favorRepository;
        this.ticketTypeRepository=ticketTypeRepository;
        this.personalTicketRepository = personalTicketRepository;
        this.userRepository=userRepository;
    }
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public CreationResponse addNewTicket(TicketCreationRequest request) {
        Optional<Ticket> ticketOptional
                =ticketRepository.findByTicketName(request.getName());
        boolean exists=ticketOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }

        //Searching for the type in the repository and returning an obj
        TicketType ticketType
                =ticketTypeRepository.findById(request.getTypeId()).orElseThrow(
                ()->new IllegalStateException("ticket whit id " + request.getTypeId() + " does not exost")
        );
        Set<TicketType> ticketTypes= new HashSet<>();
        ticketTypes.add(ticketType);


        Ticket createdTicke=
                 Ticket.builder()
                         .name(request.getName())
                         .workStart(request.getWorkStart())
                         .workEnd(request.getWorkEnd())
                         .type(ticketTypes)
                         .build();
        //ticketSet.add(createdTicke);
        //favor.setTicket(ticketSet);
        //favorRepository.save(favor);
        ticketRepository.save(createdTicke);

        Long idOfTheNewTicket =createdTicke.getId();
        return new CreationResponse(idOfTheNewTicket,"Ticket Created successfully");
    }

    public void updateTicket(String nameOfTicketToUpdate, Ticket ticket) {
        String name=ticket.getName();
        Optional<Ticket> updatedTicket=ticketRepository.findByTicketName(nameOfTicketToUpdate);
        boolean exits=updatedTicket.isPresent();
        if(exits) {
            new IllegalStateException("ticket whit name " + nameOfTicketToUpdate + " does not exist");
        }
        if(name!=null
                &&!Objects.equals(ticket.getName(),name)){
            updatedTicket.get().setName(name);
        }
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        Ticket ticket
                =ticketRepository.findById(id).orElseThrow();

        ticketRepository.delete(ticket);
    }

    public List<Ticket> findTicketByFavor(Long favorId) {
        return ticketRepository.findByTicketFavor(favorId);
    }

    public void addFavor(Long idTicket, Long idFavor) {
        //Searching for the favor from the repository
        Favor favor
                =favorRepository.findById(idFavor).orElseThrow();
        if(favor==null){
            throw new IllegalStateException("Id of the favor is not found");
        }
        Ticket ticket=findById(idTicket);
        //ticket.addFavor(favor);
        favor.addTicket(ticket);
        //ticketRepository.save(ticket);
        favorRepository.save(favor);
    }

    public Ticket findById(Long id){
        return ticketRepository.findById(id).orElseThrow();
    }

    public void addPersonalTicket(Long idTicket, Long idPersnoalTicket) {
        PersonalTicket personalTicket=
                personalTicketRepository.findById(idPersnoalTicket).orElseThrow();
        Ticket ticket=findById(idTicket);
        ticket.addPersonalTicket(personalTicket);
        ticketRepository.save(ticket);
    }
}
