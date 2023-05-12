package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
import com.diploma.ticket.system.payload.request.FavorCreationReqest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.FavorRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class FavorService {
    private final FavorRepository favorRepository;
    private static Logger logger= Logger.getLogger(FavorService.class.getName());


    @Autowired
    public FavorService(FavorRepository favorRepository){
        this.favorRepository = favorRepository;
    }

    public List<Favor> getService() {
        return favorRepository.findAll();
    }

    public CreationResponse addNewService(FavorCreationReqest favorRequest) {
        Optional<Favor> service1
                = favorRepository.findFavorByName(favorRequest.getName());
        boolean exists=service1.isPresent();
        if(exists){
            logger.info("Name of Service is taken");
            throw new IllegalArgumentException("Name of service dose not exists");
        }

        Favor favor= Favor.builder()
                .name(favorRequest.getName())
                .description(favorRequest.getDescription())
                .workStart(favorRequest.getWorkStart())
                .workEnd(favorRequest.getWorkEnd())
                .build();

        Long idOFTheNewFavor =favorRepository.save(favor).getId();
        logger.info("Service was saved to the repository");
        return new CreationResponse(idOFTheNewFavor,"Favor Created successfully");
    }

    public void updateService(
            String nameToUpdate,
            Favor favor
    ){
        String serviceName= favor.getName();
        Optional<Favor> optionalService
                = favorRepository.findFavorByName(nameToUpdate);
        boolean exists=optionalService.isPresent();
        if(exists){
            logger.info("Name of Service is taken");
            throw new IllegalStateException("service whit name"+nameToUpdate+"exists");
        }
        if(serviceName!=null
                &&!Objects.equals(favor.getName(),serviceName)){
            optionalService.get().setName(serviceName);
        }
        logger.info("Service was updated");
        favorRepository.save(favor);
    }

    public Favor findFavorFromRepository(Long id) throws NotFountInRepositoryException {
        Favor favor
                =favorRepository.findById(id).orElseThrow(
                ()-> new NotFountInRepositoryException
                        ("Favor whit id:"+id+"was not found")
        );
        logger.info("Favor whit id:"+id+"was found");
        return favor;
    }

    public void deleteFavor(Long id){
        Favor favor= null;
        try {
            favor = findFavorFromRepository(id);
        } catch (Exception e) {
            throw  new IllegalStateException(e);
        }
        logger.info("Favor whit id:"+id+"was deleted");
        favorRepository.delete(favor);
    }
}
