package com.diploma.TicketSystem.service;

import com.diploma.TicketSystem.entity.PersonalTicket;
import com.diploma.TicketSystem.repository.PersonalTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonalTicketService {

    private final PersonalTicketRepository personalTicketRepository;

    @Autowired
    public PersonalTicketService(PersonalTicketRepository personalTicketRepository){
        this.personalTicketRepository=personalTicketRepository;
    }

    public void updatePersonalTicket(Long numberToUpdate, PersonalTicket personalTicket) {
        Long number=personalTicket.getNumber();
        Optional<PersonalTicket> updatedTicket=personalTicketRepository.findPersonalTicketByNUmber(numberToUpdate);
        boolean exits=updatedTicket.isPresent();
        if(exits) {
            new IllegalStateException("ticket whit name " + numberToUpdate + " does not exost");
        }
        if(number!=null
                &&!Objects.equals(personalTicket.getNumber(),number)){
            updatedTicket.get().setNumber(number);
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
