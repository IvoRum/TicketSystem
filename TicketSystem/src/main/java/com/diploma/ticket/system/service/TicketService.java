package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.*;
import com.diploma.ticket.system.payload.request.TicketCreationRequest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.*;
import org.apache.log4j.Logger;
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
    private static Logger logger= Logger.getLogger(TicketService.class.getName());

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
        String ticketName=request.getName();
        Optional<Ticket> ticketOptional
                =ticketRepository.findByTicketName(ticketName);
        boolean exists=ticketOptional.isPresent();
        if(exists){
            logger.info("Ticket whit Name:"+ticketName+" is taken");
            throw new IllegalStateException("Name:"+ticketName+" is taken");
        }

        //Searching for the type in the repository and returning an obj
        TicketType ticketType
                =ticketTypeRepository.findById(request.getTypeId()).orElseThrow(
                ()->new IllegalStateException("Ticket whit id " + request.getTypeId() + " does not exost")
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

        ticketRepository.save(createdTicke);
        Long idOfTheNewTicket =createdTicke.getId();
        logger.info("Ticket whit Name:"+ticketName+" has bean saved to the repository successfully");
        return new CreationResponse(idOfTheNewTicket,"Ticket Created successfully");
    }

    public void updateTicket(String nameOfTicketToUpdate, Ticket ticket) {
        String name=ticket.getName();
        Optional<Ticket> updatedTicket=ticketRepository.findByTicketName(nameOfTicketToUpdate);
        boolean exits=updatedTicket.isPresent();
        if(exits) {
            logger.info("Ticket whit name " + nameOfTicketToUpdate + " does not exist");
            new IllegalStateException("ticket whit name " + nameOfTicketToUpdate + " does not exist");
        }
        if(name!=null
                &&!Objects.equals(ticket.getName(),name)){
            updatedTicket.get().setName(name);
        }
        ticketRepository.save(ticket);
        logger.info("Ticket whit name " + nameOfTicketToUpdate + " has bean saved to the repository");
    }

    public void deleteTicket(Long id) {
        Ticket ticket
                =ticketRepository.findById(id).orElseThrow();
        ticketRepository.delete(ticket);
        logger.info("Ticket whit id " + id + " has bean deleted from the repository");
    }

    public List<Ticket> findTicketByFavor(Long favorId) {
        return ticketRepository.findByTicketFavor(favorId);
    }

    public void addFavor(Long idTicket, Long idFavor) {
        //Searching for the favor from the repository
        Favor favor
                =favorRepository.findById(idFavor).orElseThrow();
        if(favor==null){
            logger.info("Id: " + idFavor + " of the favor is not found");
            throw new IllegalStateException("Id:"+idFavor+" of the favor is not found");
        }
        Ticket ticket=findById(idTicket);
        favor.addTicket(ticket);
        favorRepository.save(favor);
        logger.info("Ticket with id: " + idTicket + "and Favor whit id:"+idFavor+" have been saved to the repository.");
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
        logger.info("Ticket with id: " + idTicket + " hase bean saved to the repository");
    }
}
