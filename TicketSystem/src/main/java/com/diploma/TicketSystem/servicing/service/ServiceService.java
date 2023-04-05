package com.diploma.TicketSystem.servicing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository){
        this.serviceRepository=serviceRepository;
    }

    public List<com.diploma.TicketSystem.servicing.service.Service> getService() {
        return serviceRepository.findAll();
    }

    public void addNewService(com.diploma.TicketSystem.servicing.service.Service service) {
        Optional<com.diploma.TicketSystem.servicing.service.Service> service1
                =serviceRepository.findServiceByName(service.getName());
        boolean exists=service1.isPresent();
        if(exists){
            throw new IllegalArgumentException("Name of service dose not exists");
        }
        serviceRepository.save(service);
    }

    public void updateService(String nameToUpdate, com.diploma.TicketSystem.servicing.service.Service service) {
        String serviceName= service.getName();
        Optional< com.diploma.TicketSystem.servicing.service.Service> optionalService
                =serviceRepository.findServiceByName(nameToUpdate);
        boolean exists=optionalService.isPresent();
        if(exists){
            new IllegalStateException("service whit name"+nameToUpdate+"exists");
        }
        if(serviceName!=null
                &&!Objects.equals(service.getName(),serviceName)){
            optionalService.get().setName(serviceName);
        }
        serviceRepository.save(service);

    }
}
