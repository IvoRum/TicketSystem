package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.repository.PersonalTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class PersonalTicketService {

    private final PersonalTicketRepository personalTicketRepository;

    @Autowired
    public PersonalTicketService(PersonalTicketRepository personalTicketRepository){
        this.personalTicketRepository=personalTicketRepository;
    }

    public void updatePersonalTicket(Long numberToUpdate, PersonalTicket personalTicket) {
        Long number=personalTicket.getNumber();
        PersonalTicket updatedTicket=
                personalTicketRepository.findPersonalTicketByNUmber(numberToUpdate).orElseThrow(
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


    public void addNewPersonalTicket(PersonalTicket personalTicket) {
        Optional<PersonalTicket> ticketOptional
                =personalTicketRepository.findPersonalTicketByNUmber(personalTicket.getNumber());
        boolean exists=ticketOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Number is taken");
        }
        personalTicketRepository.save(personalTicket);
    }
}
