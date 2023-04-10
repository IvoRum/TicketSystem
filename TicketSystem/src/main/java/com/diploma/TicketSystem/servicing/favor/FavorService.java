package com.diploma.TicketSystem.servicing.favor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FavorService {
    private final FavorRepository favorRepository;

    @Autowired
    public FavorService(FavorRepository favorRepository){
        this.favorRepository = favorRepository;
    }

    public List<Favor> getService() {
        return favorRepository.findAll();
    }

    public void addNewService(Favor favor) {
        Optional<Favor> service1
                = favorRepository.findServiceByName(favor.getName());
        boolean exists=service1.isPresent();
        if(exists){
            throw new IllegalArgumentException("Name of service dose not exists");
        }
        favorRepository.save(favor);
    }

    public void updateService(String nameToUpdate, Favor favor) {
        String serviceName= favor.getName();
        Optional<Favor> optionalService
                = favorRepository.findServiceByName(nameToUpdate);
        boolean exists=optionalService.isPresent();
        if(exists){
            new IllegalStateException("service whit name"+nameToUpdate+"exists");
        }
        if(serviceName!=null
                &&!Objects.equals(favor.getName(),serviceName)){
            optionalService.get().setName(serviceName);
        }
        favorRepository.save(favor);

    }
}
