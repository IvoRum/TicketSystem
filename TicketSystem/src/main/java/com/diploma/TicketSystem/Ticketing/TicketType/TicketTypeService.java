package com.diploma.TicketSystem.Ticketing.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    @Autowired
    public TicketTypeService(TicketTypeRepository ticketTypeRepository){
        this.ticketTypeRepository=ticketTypeRepository;
    }
//TODO test getTicketType
    public List<TicketType> getTicketType() {
        return ticketTypeRepository.findAll();
    }

    public void addNewTicketType(TicketType ticketType) {
        Optional<TicketType> ticketOptional
                =ticketTypeRepository.findByTicketName(ticketType.getName());
        if(ticketOptional.isPresent()){
            throw new IllegalStateException("Number is taken");
        }
        ticketTypeRepository.save(ticketType);
    }
//TODO test updateTicketTpy
    public void updateTicketType(Long id, TicketType ticketType) {
        String name=ticketType.getName();
        TicketType updatedTicket=ticketTypeRepository.findById(id).orElseThrow(()->
                new IllegalStateException("ticket type whit id "+ id+" or does not exost")
        );
        if(name!=null
                &&!Objects.equals(ticketType.getName(),name)){
            updatedTicket.setName(name);
        }
    }
}
