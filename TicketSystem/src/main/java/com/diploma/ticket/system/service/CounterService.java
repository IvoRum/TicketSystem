package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.CounterRepository;
import com.diploma.ticket.system.repository.FavorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class  CounterService {

    private final CounterRepository counterRepository;
    private final FavorRepository favorRepository;
    @Autowired
    public CounterService(
            CounterRepository counterRepository,
            FavorRepository favorTypeRepository
    ){
        this.counterRepository=counterRepository;
        this.favorRepository=favorTypeRepository;
    }
    public CreationResponse addNewCounter(Counter counter) {
        Optional<Counter> counterOptional
                =counterRepository.findCounterByName(counter.getName());
        boolean exists=counterOptional.isPresent();
        if(exists){
            throw new IllegalArgumentException("Name is taken");
        }
        counterRepository.save(counter);
        return new CreationResponse(counter.getId(),
                "Counter was created successfully!");
    }

    public List<Counter> getCounters() {
        return counterRepository.findAll();
    }

    public void updateCounter(
            String nameOfCounterToUpdate,
            Counter counter
    ){
        String name=counter.getName();
        Optional<Counter> updatedCounter=counterRepository.findCounterByName(nameOfCounterToUpdate);
        boolean exits=updatedCounter.isPresent();
        if(exits) {
            new IllegalArgumentException("counter whit name " + nameOfCounterToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(counter.getName(),name)){
            updatedCounter.get().setName(name);
        }
        counterRepository.save(counter);
    }



    public CreationResponse addNewFavor(Long favorId, Long counterId) {
        CreationResponse creationResponse=null;
        Favor favor
                =favorRepository.findById(favorId).orElseThrow(
                ()-> new IllegalArgumentException("FavorType whit ID:"+favorId+" dose not exist!")
        );
        Counter counter =findCounter(counterId);
        counter.addFavorType(favor);
        counterRepository.save(counter);
        return new CreationResponse(favorId,
                "Now has a new favor type whit the name: "+favor.getName());
    }

    public void deleteCounter(Long id) {
        Counter counter
                =counterRepository.findById(id).orElseThrow();

        counterRepository.delete(counter);
    }

    public Counter findCounter(Long counterId) {
        Counter counter
                =counterRepository.findById(counterId).orElseThrow();
        return counter;
    }
}
