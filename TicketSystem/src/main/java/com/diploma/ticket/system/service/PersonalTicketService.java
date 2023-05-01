package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.PersonalTicketRepository;
import com.diploma.ticket.system.repository.UserRepository;
import com.diploma.ticket.system.util.JwtUtil;
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
            JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.personalTicketRepository=personalTicketRepository;
        this.jwtUtil = jwtUtil;
    }

    public void updatePersonalTicket(Long numberToUpdate, PersonalTicket personalTicket) {
        Long number=personalTicket.getNumber();
        PersonalTicket updatedTicket=
                null;
        try {
            updatedTicket = findPersonalTicketByIdOfPersonalTicket(numberToUpdate);
        } catch (NotFountInRepositoryException e) {
            throw new IllegalArgumentException(e);
        }
        if(number!=null
                &&!Objects.equals(personalTicket.getNumber(),number)){
            updatedTicket.setNumber(number);
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
            ticketOptional = findPersonalTicketByIdOfPersonalTicket(personalTicket.getNumber());
            throw new IllegalStateException("Number is taken");
        } catch (NotFountInRepositoryException e) {
            personalTicketRepository.save(personalTicket);
            return new CreationResponse(personalTicket.getNumber(),
                    "Personal Ticket Created!");
        }
    }

    public CreationResponse finishTicket(
            Long tickeNumber,
            String authHeader
            ) {
        PersonalTicket finishedPersonalTicket
                = null;
        try {
            finishedPersonalTicket = findPersonalTicketByIdOfPersonalTicket(tickeNumber);
        } catch (NotFountInRepositoryException e) {
            throw new IllegalArgumentException(e);
        }
        if(finishedPersonalTicket.getFinishTime()!=null){
            throw new IllegalStateException();
        }
        //Searching for the user
        final String userEmail;
        final String jwtToken;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new IllegalStateException();
        }
        jwtToken = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(jwtToken);

        User userThatHasFinishedTheTicket
                =userRepository.findByEmail(userEmail).orElseThrow(
                ()-> new IllegalStateException(
                        "No user whit email "+userEmail+"i found!")
        );
        //TODO pitai dali e edekvatno
        Time sqlTime=new Time(new java.util.Date().getTime());
        finishedPersonalTicket.setFinishTime(sqlTime);
        finishedPersonalTicket.setActive(false);
        userThatHasFinishedTheTicket.addPersonalTicket(finishedPersonalTicket);
        userRepository.save(userThatHasFinishedTheTicket);
        personalTicketRepository.save(finishedPersonalTicket);
        return new CreationResponse(tickeNumber,"Finished on time:");
    }

    public PersonalTicket findPersonalTicketByIdOfPersonalTicket(Long numberOfPersonalTicket) throws NotFountInRepositoryException {
        PersonalTicket personalTicket
                =personalTicketRepository.findById(numberOfPersonalTicket).orElseThrow(
                ()-> new NotFountInRepositoryException(
                        "No personal Ticket With number:"+numberOfPersonalTicket+" exists!")
        );

        return personalTicket;
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
}
