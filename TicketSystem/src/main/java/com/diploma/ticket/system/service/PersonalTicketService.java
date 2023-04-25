package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.PersonalTicketRepository;
import com.diploma.ticket.system.repository.UserRepository;
import com.diploma.ticket.system.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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
                personalTicketRepository
                        .findPersonalTicketByNUmber(numberToUpdate).orElseThrow(
                        ()->new IllegalStateException("ticket whit name " + numberToUpdate + " does not exost")
        );
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
        Optional<PersonalTicket> ticketOptional
                =personalTicketRepository
                .findPersonalTicketByNUmber(personalTicket.getNumber());
        boolean exists=ticketOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Number is taken");
        }
        personalTicketRepository.save(personalTicket);
        return new CreationResponse(personalTicket.getNumber(),
                "Personal Ticket Created!");
    }

    public CreationResponse finishTicket(
            Long tickeNumber,
            String authHeader
            ) {
        PersonalTicket finishedPersonalTicket
                =personalTicketRepository.findById(tickeNumber).orElseThrow(
                ()-> new IllegalStateException(
                        "No personal Ticket Wiht number:"+tickeNumber+" exists!")
        );
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
}
