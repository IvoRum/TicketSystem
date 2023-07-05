package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.PersonalTicketRepository;
import com.diploma.ticket.system.repository.UserRepository;
import com.diploma.ticket.system.util.JwtUtil;
import org.apache.log4j.Logger;
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
    private static Logger logger= Logger.getLogger(PersonalTicketService.class.getName());

    private final TicketService ticketService;

    @Autowired
    public PersonalTicketService(
            PersonalTicketRepository personalTicketRepository,
            UserRepository userRepository,
            JwtUtil jwtUtil,
            TicketService ticketService){
        this.userRepository=userRepository;
        this.personalTicketRepository=personalTicketRepository;
        this.jwtUtil = jwtUtil;
        this.ticketService = ticketService;
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
            logger.info("Id:"+numberToUpdate+" of Personal Ticket is taken");
            throw new IllegalArgumentException(e);
        }
        if(number!=null
                &&!Objects.equals(personalTicket.getId(),number)){
            updatedTicket.setId(number);
        }
        logger.info("Personal ticket whit id:"+personalTicket.getId()+" has bean updated to the repository");
        personalTicketRepository.save(personalTicket);
    }

    public List<PersonalTicket> getPersonaTickets() {
        return personalTicketRepository.findAll();
    }


    public PersonalTicket addNewPersonalTicket(PersonalTicket personalTicket) {
        PersonalTicket ticketOptional
                 = null;
        Ticket ticket=null;
        try {
            ticketOptional = findPersonalTicketByIdOfPersonalTicket(personalTicket.getId());

            logger.info("Id:"+personalTicket.getId()+" of Personal Ticket is taken");
            throw new IllegalStateException("Number is taken");
        } catch (NotFountInRepositoryException e) {

            personalTicketRepository.save(personalTicket);
            logger.info("Personal ticket whit id:"+personalTicket.getId()+" has bean saved to the repository");
            return personalTicket;
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
            logger.info("Id:"+ticketNumber+" of Personal Ticket was not found");
            throw new IllegalArgumentException(e);
        }
        if(finishedPersonalTicket.getFinishTime()!=null){
            logger.info("The ticket whit id:"+ticketNumber+"was empty");
            throw new IllegalStateException();
        }
        User userThatHasFinishedTheTicket=findUserByHeader(authHeader);

        Time sqlTime=new Time(new java.util.Date().getTime());
        finishedPersonalTicket.setFinishTime(sqlTime);
        finishedPersonalTicket.setActive(false);
        userThatHasFinishedTheTicket.addPersonalTicket(finishedPersonalTicket);
        userRepository.save(userThatHasFinishedTheTicket);
        personalTicketRepository.save(finishedPersonalTicket);
        logger.info("The personal ticket whit id:"+ticketNumber+"was finished successfully");
        return new CreationResponse(ticketNumber,"Finished on time:");
    }

    public PersonalTicket findPersonalTicketByIdOfPersonalTicket(
            Long numberOfPersonalTicket
    ) throws NotFountInRepositoryException
    {
        logger.info("Finding personal ticket was invoiced ");
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
            logger.info("Personal Ticket whit id: "+id+"was not found");
            throw new IllegalArgumentException(e);
        }
        logger.info("Personal Ticket whit id: "+id+"was deleted successfully");
        personalTicketRepository.delete(personalTicket);
    }

    public List<PersonalTicket> findActivePersonalTicketByTicket(Long ticketId) {
        logger.info("Find Personal Ticket by ticket was invoked");
        return personalTicketRepository.findPersonalTicketsByTicket(ticketId);

    }
    //TODO remove this
    public boolean setTicketToUser(
            String authHeader,
            PersonalTicket personalTicket
    ){
        User user=findUserByHeader(authHeader);
        try {
            personalTicket.setActive(false);
            personalTicketRepository.save(personalTicket);
            user.addPersonalTicket(personalTicket);

            userRepository.save(user);
        } catch (Exception e) {
            logger.info("Ticket was not set set to the user");
            return false;
        }
        return true;
    }
    //TODO remove this
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

    public Long getLastPersonTicketId() {
        PersonalTicket lastTicket=personalTicketRepository.findLastPersonalTicket();
        return lastTicket.getId();
    }


    public List<PersonalTicket> findAllAndJoinTicket(){
        //return personalTicketRepository.findAllAndJoinTicket();
        return null;
    }

}
