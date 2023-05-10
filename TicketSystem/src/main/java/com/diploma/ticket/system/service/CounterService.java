package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.CounterRepository;
import com.diploma.ticket.system.repository.FavorRepository;
import org.apache.log4j.Logger;
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
    private static Logger logger= Logger.getLogger(CounterService.class.getName());
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
            logger.info("Name of counter is taken");
            throw new IllegalArgumentException("Name is taken");
        }
        counterRepository.save(counter);
        logger.info("Counter was created");
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
            logger.info("Name of counter is taken");
            new IllegalArgumentException("counter whit name " + nameOfCounterToUpdate + " does not exist");
        }
        if(name!=null
                &&!Objects.equals(counter.getName(),name)){
            updatedCounter.get().setName(name);
        }
        counterRepository.save(counter);
        logger.info("Counter was Updated");
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
        logger.info("Favor was added to the Counter");
        return new CreationResponse(favorId,
                "Now has a new favor type whit the name: "+favor.getName());
    }

    public void deleteCounter(Long id) {
        Counter counter
                =counterRepository.findById(id).orElseThrow();
        logger.info("Counter was deleted form the repository");
        counterRepository.delete(counter);
    }

    public Counter findCounter(Long counterId) {
        Counter counter
                =counterRepository.findById(counterId).orElseThrow();
        return counter;
    }
}
