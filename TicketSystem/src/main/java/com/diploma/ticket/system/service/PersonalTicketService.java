package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.PersonalTicketRepository;
import com.diploma.ticket.system.repository.UserRepository;
import org.hibernate.type.descriptor.java.LocalTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PersonalTicketService {

    private final PersonalTicketRepository personalTicketRepository;
    private final UserRepository userRepository;

    @Autowired
    public PersonalTicketService(
            PersonalTicketRepository personalTicketRepository,
            UserRepository userRepository
    ){
        this.userRepository=userRepository;
        this.personalTicketRepository=personalTicketRepository;
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

    public CreationResponse finishTicket(Long tickeNumber) {
        PersonalTicket personalTicket
                =personalTicketRepository.findById(tickeNumber).orElseThrow(
                ()-> new IllegalStateException(
                        "No personal Ticket Wiht number:"+tickeNumber+" exists!")
        );
        if(personalTicket.getFinishTime()!=null){
            throw new IllegalStateException();
        }
        Time sqlTime=new Time(new java.util.Date().getTime());
        personalTicket.setFinishTime(sqlTime);
        personalTicket.setActive(false);
        personalTicketRepository.save(personalTicket);
        return new CreationResponse(tickeNumber,"Finished on time:");
    }
}
