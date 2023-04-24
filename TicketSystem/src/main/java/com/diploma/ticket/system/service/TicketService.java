package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.TicketType;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.payload.request.TicketCreationRequest;
import com.diploma.ticket.system.payload.response.TicketCreationResponse;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.repository.FavorRepository;
import com.diploma.ticket.system.repository.TicketRepository;
import com.diploma.ticket.system.repository.TicketTypeRepository;
import com.diploma.ticket.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FavorRepository favorRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketService(
            TicketRepository ticketRepository,
            FavorRepository favorRepository,
            TicketTypeRepository ticketTypeRepository,
            UserRepository userRepository
    ){
        this.ticketRepository=ticketRepository;
        this.favorRepository=favorRepository;
        this.ticketTypeRepository=ticketTypeRepository;
        this.userRepository=userRepository;
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
        //Searching for the favor from the repository
        //TODO Pitai koe ot dveto e po razumno
        Favor favor
                =favorRepository.getReferenceById(request.getFavorId());
        if(favor==null){
            throw new IllegalStateException("Id of the favor is not found");
        }

        Set<Favor> favors=new HashSet<>();
        favors.add(favor);
        //Searching for the type in the repository and returning an obj
        TicketType ticketType
                =ticketTypeRepository.findById(request.getTypeId()).orElseThrow(
                ()->new IllegalStateException("ticket whit name " + request.getTypeId() + " does not exost")
        );
        List<TicketType> ticketTypes= new ArrayList<>();
        ticketTypes.add(ticketType);


        Ticket createdTicke=
                 Ticket.builder()
                         .name(request.getName())
                         .workStart(request.getWorkStart())
                         .workEnd(request.getWorkEnd())
                         .favors(favors)
                         .type(ticketTypes)
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
            new IllegalStateException("ticket whit name " + nameOfTicketToUpdate + " does not exist");
        }
        if(name!=null
                &&!Objects.equals(ticket.getName(),name)){
            updatedTicket.get().setName(name);
        }
        ticketRepository.save(ticket);
    }

    public String addUserToTicket(Integer userId,Long ticketId) {
        User user
                =userRepository.findById(userId).orElseThrow(
                ()->new IllegalStateException("User whit ID:"+userId+"dose not exist!")
        );
        Ticket ticket
                =ticketRepository.findById(ticketId).orElseThrow(
                ()->new IllegalStateException("Ticket whit ID:"+ticketId+"does not exist!")
        );

        ticket.addUser(user);
        ticketRepository.save(ticket);

        return "Successfully added user to the ticket";
    }
}
