package com.diploma.TicketSystem.service;

import com.diploma.TicketSystem.entity.FavorType;
import com.diploma.TicketSystem.repository.FavorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FavorTypeService {

    public final FavorTypeRepository favorTypeRepository;

    @Autowired
    public FavorTypeService(FavorTypeRepository favorTypeRepository){
        this.favorTypeRepository = favorTypeRepository;
    }

    public List<FavorType> getServiceTypes() {
        return favorTypeRepository.findAll();
    }

    public void addNewServiceType(FavorType favorType) {
        Optional<FavorType> serviceTypeOptional
                = favorTypeRepository.findServiceTypeByName(favorType.getName());
        boolean exists=serviceTypeOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }
        favorTypeRepository.save(favorType);
    }

    public void updateTicket(String nameOfServiceTypToUpdate, FavorType favorType) {
        String name= favorType.getName();
        Optional<FavorType> updatedServiceType= favorTypeRepository.findServiceTypeByName(nameOfServiceTypToUpdate);
        boolean exits=updatedServiceType.isPresent();
        if(exits) {
            new IllegalStateException("ticket whit name " + nameOfServiceTypToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(favorType.getName(),name)){
            updatedServiceType.get().setName(name);
        }
        favorTypeRepository.save(favorType);

    }
}
