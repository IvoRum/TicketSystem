package com.diploma.ticket.system.service;

import com.diploma.ticket.system.repository.TicketTypeRepository;
import com.diploma.ticket.system.entity.TicketType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@Transactional
public class TicketTypeService {
    private final TicketTypeRepository ticketRepository;
    private static Logger logger= Logger.getLogger(TicketTypeService.class.getName());
    @Autowired
    public TicketTypeService(TicketTypeRepository ticketTypeRepository){this.ticketRepository=ticketTypeRepository;}
    public List<TicketType> getTicketTypes() {
        return ticketRepository.findAll();
    }

    public void updateTicket(String nameOfTicketTypeTochainge, TicketType ticket) {
        String name=ticket.getName();
        Optional<TicketType> updatedTicket=ticketRepository.findByTicketTypesName(nameOfTicketTypeTochainge);
        boolean exits=updatedTicket.isPresent();
        if(exits) {
            logger.info("Ticket type whit Name:"+nameOfTicketTypeTochainge+" does not exost");
            new IllegalStateException("ticket type whit name " + nameOfTicketTypeTochainge + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(ticket.getName(),name)){
            updatedTicket.get().setName(name);
        }
        ticketRepository.save(ticket);
        logger.info("Ticket type whit Name:"+nameOfTicketTypeTochainge+" has bean saved to the repository");
    }

    public TicketType addNewTicket(TicketType ticketType) {
        Optional<TicketType> ticketOptional
                =ticketRepository.findByTicketTypesName(ticketType.getName());
        boolean exists=ticketOptional.isPresent();
        if(exists){
            new IllegalStateException("ticket type whit name " + ticketType.getName() + " is taken");
            throw new IllegalArgumentException("Name is taken");
        }
        ticketRepository.save(ticketType);
        logger.info("Ticket type whit Name:"+ticketType.getName()+" has bean updated and  saved to the repository");
        return ticketType;
    }

    public void deleteTickeType(Long id) {
        TicketType ticketType
                =ticketRepository.findById(id).orElseThrow();
        logger.info("Ticket type whit Id:"+id+" has bean deleted from the repository");
        ticketRepository.delete(ticketType);
    }
}
