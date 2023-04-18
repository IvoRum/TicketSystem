package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class  CounterService {

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
        Optional<Counter> updatedCounter=counterRepository.findCounterByName(nameOfCounterToUpdate);
        boolean exits=updatedCounter.isPresent();
        if(exits) {
            new IllegalStateException("counter whit name " + nameOfCounterToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(counter.getName(),name)){
            updatedCounter.get().setName(name);
        }
        counterRepository.save(counter);
    }
}
