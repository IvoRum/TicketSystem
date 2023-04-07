package com.diploma.TicketSystem.conuter;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CounterService {

    private final CounterRepository counterRepository;
    @Autowired
    public CounterService(CounterRepository counterRepository){
        this.counterRepository=counterRepository;
    }
    public void addNewCounter(Counter counter) {
        Optional<Counter> counterOptional
                =counterRepository.findCounterByName(counter.getName());
        boolean exists=counterOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }
        counterRepository.save(counter);
    }

    public List<Counter> getCounters() {
        return counterRepository.findAll();
    }

    public void updateCounter(String nameOfCounterToUpdate, Counter counter) {
        String name=counter.getName();
        Optional<Counter> updatedTicket=counterRepository.findCounterByName(nameOfCounterToUpdate);
        boolean exits=updatedTicket.isPresent();
        if(exits) {
            new IllegalStateException("ticket whit name " + nameOfCounterToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(counter.getName(),name)){
            updatedTicket.get().setName(name);
        }
        counterRepository.save(counter);
    }
}
