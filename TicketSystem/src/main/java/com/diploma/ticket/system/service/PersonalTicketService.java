package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.PersonalTicketRepository;
import com.diploma.ticket.system.repository.UserRepository;
import com.diploma.ticket.system.util.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PersonalTicketService {

    private final PersonalTicketRepository personalTicketRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public PersonalTicketService(
            PersonalTicketRepository personalTicketRepository,
            UserRepository userRepository,
            JwtUtil jwtUtil
    ){
        this.userRepository=userRepository;
        this.personalTicketRepository=personalTicketRepository;
        this.jwtUtil = jwtUtil;
    }

    public void updatePersonalTicket(
            Long numberToUpdate,
            @NotNull PersonalTicket personalTicket
    ){
        Long number=personalTicket.getId();
        PersonalTicket updatedTicket=
                null;
        try {
            updatedTicket = findPersonalTicketByIdOfPersonalTicket(numberToUpdate);
        } catch (NotFountInRepositoryException e) {
            throw new IllegalArgumentException(e);
        }
        if(number!=null
                &&!Objects.equals(personalTicket.getId(),number)){
            updatedTicket.setId(number);
        }
        personalTicketRepository.save(personalTicket);
    }

    public List<PersonalTicket> getPersonaTickets() {
        return personalTicketRepository.findAll();
    }


    public CreationResponse addNewPersonalTicket(PersonalTicket personalTicket) {
        PersonalTicket ticketOptional
                 = null;
        try {
            ticketOptional = findPersonalTicketByIdOfPersonalTicket(personalTicket.getId());
            throw new IllegalStateException("Number is taken");
        } catch (NotFountInRepositoryException e) {
            personalTicketRepository.save(personalTicket);
            return new CreationResponse(personalTicket.getId(),
                    "Personal Ticket Created!");
        }
    }

    public CreationResponse finishTicket(
            Long ticketNumber,
            String authHeader
            ) {
        PersonalTicket finishedPersonalTicket
                = null;
        try {
            finishedPersonalTicket = findPersonalTicketByIdOfPersonalTicket(ticketNumber);
        } catch (NotFountInRepositoryException e) {
            throw new IllegalArgumentException(e);
        }
        if(finishedPersonalTicket.getFinishTime()!=null){
            throw new IllegalStateException();
        }
        User userThatHasFinishedTheTicket=findUserByHeader(authHeader);

        Time sqlTime=new Time(new java.util.Date().getTime());
        finishedPersonalTicket.setFinishTime(sqlTime);
        finishedPersonalTicket.setActive(false);
        userThatHasFinishedTheTicket.addPersonalTicket(finishedPersonalTicket);

        userRepository.save(userThatHasFinishedTheTicket);
        personalTicketRepository.save(finishedPersonalTicket);
        return new CreationResponse(ticketNumber,"Finished on time:");
    }

    public PersonalTicket findPersonalTicketByIdOfPersonalTicket(
            Long numberOfPersonalTicket
    ) throws NotFountInRepositoryException
    {
        return personalTicketRepository.findById(numberOfPersonalTicket).orElseThrow(
                ()-> new NotFountInRepositoryException(
                        "No personal Ticket With number:"+numberOfPersonalTicket+" exists!")
        );
    }

    public void deletePersonalTicket(Long id){
        PersonalTicket personalTicket= null;
        try {
            personalTicket = findPersonalTicketByIdOfPersonalTicket(id);
        } catch (NotFountInRepositoryException e) {
            throw new IllegalArgumentException(e);
        }

        personalTicketRepository.delete(personalTicket);
    }

    public List<PersonalTicket> findActivePersonalTicketByTicket(Long ticketId) {

        return personalTicketRepository.findPersonalTicketsByTicket(ticketId);

    }

    public boolean setTicketToUser(
            String authHeader,
            PersonalTicket personalTicket
    ){
        User user=findUserByHeader(authHeader);
        try {
            personalTicket.setActive(false);
            user.addPersonalTicket(personalTicket);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    private User findUserByHeader(String authHeader){
        //Searching for the user
        final String userEmail;
        final String jwtToken;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new IllegalStateException();
        }
        jwtToken = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(jwtToken);

        User user
                =userRepository.findByEmail(userEmail).orElseThrow(
                ()-> new IllegalStateException(
                        "No user whit email "+userEmail+"i found!")
        );
        return user;
    }
}
