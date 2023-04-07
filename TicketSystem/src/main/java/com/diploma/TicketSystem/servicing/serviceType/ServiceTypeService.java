package com.diploma.TicketSystem.servicing.serviceType;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceTypeService {

    public final ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository){
        this.serviceTypeRepository=serviceTypeRepository;
    }

    public List<ServiceType> getServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    public void addNewServiceType(ServiceType serviceType) {
        Optional<ServiceType> serviceTypeOptional
                =serviceTypeRepository.findServiceTypeByName(serviceType.getName());
        boolean exists=serviceTypeOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }
        serviceTypeRepository.save(serviceType);
    }

    public void updateTicket(String nameOfServiceTypToUpdate, ServiceType serviceType) {
        String name=serviceType.getName();
        Optional<ServiceType> updatedServiceType=serviceTypeRepository.findServiceTypeByName(nameOfServiceTypToUpdate);
        boolean exits=updatedServiceType.isPresent();
        if(exits) {
            new IllegalStateException("ticket whit name " + nameOfServiceTypToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(serviceType.getName(),name)){
            updatedServiceType.get().setName(name);
        }
        serviceTypeRepository.save(serviceType);

    }
}
