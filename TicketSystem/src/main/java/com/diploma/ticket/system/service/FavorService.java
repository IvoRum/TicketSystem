package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.FavorType;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.payload.request.FavorCreationReqest;
import com.diploma.ticket.system.payload.response.FavorCreationResponce;
import com.diploma.ticket.system.payload.response.TicketCreationResponse;
import com.diploma.ticket.system.repository.FavorRepository;
import com.diploma.ticket.system.repository.FavorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavorService {
    private final FavorRepository favorRepository;
    private final FavorTypeRepository favorTypeRepository;

    @Autowired
    public FavorService(FavorRepository favorRepository, FavorTypeRepository favorTypeRepository){
        this.favorRepository = favorRepository;
        this.favorTypeRepository=favorTypeRepository;
    }

    public List<Favor> getService() {
        return favorRepository.findAll();
    }

    public FavorCreationResponce addNewService(FavorCreationReqest favorRequest) {
        Optional<Favor> service1
                = favorRepository.findServiceByName(favorRequest.getName());
        boolean exists=service1.isPresent();
        if(exists){
            throw new IllegalArgumentException("Name of service dose not exists");
        }
        //Finding all the Favor types that the favor has

        List<FavorType> favorTypes=new ArrayList<>();
        for (Long i:favorRequest.getIdsOfTypeOfFavors()) {
            favorTypes.add(favorTypeRepository.findById(i).get());
        }


        Favor favor= Favor.builder()
                .name(favorRequest.getName())
                .description(favorRequest.getDescription())
                .workStart(favorRequest.getWorkStart())
                .workEnd(favorRequest.getWorkEnd())
                .type(favorTypes)
                .build();

        Long idOFTheNewFavor =favorRepository.save(favor).getId();
        //Long idOFTheNewFavor=favor.getId();
        return new FavorCreationResponce(idOFTheNewFavor,"Favor Created successfully");
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
