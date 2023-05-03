package com.diploma.ticket.system.service;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.Machine;
import com.diploma.ticket.system.payload.request.MachineCreationRequest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.FavorRepository;
import com.diploma.ticket.system.repository.MachineRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class MachineService {
    private final MachineRepository machineRepository;
    private final FavorRepository favorRepository;
    private static Logger logger= Logger.getLogger(MachineService.class.getName());
    @Autowired
    public MachineService(
            MachineRepository machineRepository,
            FavorRepository favorRepository)
    {
        this.machineRepository = machineRepository;
        this.favorRepository = favorRepository;
    }



    public List<Machine> getMachines() {
        return machineRepository.findAll();
    }

    public CreationResponse addNewMachine(MachineCreationRequest request) {
        Optional<Machine> machineOptional
                =machineRepository.findMachineByName(request.getName());
        boolean exists=machineOptional.isPresent();
        if(exists){
            logger.info("Name:"+request.getName()+" of machine is taken");
            throw new IllegalStateException("Name is taken");
        }
        Favor favor
                =favorRepository.findById(request.getFavorId()).orElseThrow(
                ()->new IllegalStateException("FavorType whit id:"+request.getFavorId()+"dose not exist!")
        );

        List<Favor> favors=new ArrayList<>();
        favors.add(favor);

        Machine machine=
                Machine.builder()
                        .name(request.getName())
                        .type(request.getType())
                        .favor(favors)
                        .build();

        machineRepository.save(machine);
        Long idOfNewMachine= machine.getId();
        logger.info("Machine whit id:"+idOfNewMachine+" has bean saved to the repository");
        return new CreationResponse(idOfNewMachine,"Machine create successfully!");
    }

    public void updateMachine(
            String nameOfMachineToUpdate, Machine machine
    ){
        String name=machine.getName();
        Optional<Machine> updatedMachine
                =machineRepository.findMachineByName(nameOfMachineToUpdate);
        boolean exits=updatedMachine.isPresent();
        if(exits) {
            logger.info("machine whit name " + nameOfMachineToUpdate + " does not exost");
            new IllegalStateException("machine whit name " + nameOfMachineToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(machine.getName(),name)){
            updatedMachine.get().setName(name);
        }
        machineRepository.save(machine);
        logger.info("Machine whit id:"+updatedMachine.get()+" has bean updated and saved to the repository");
    }

    public void deleteMachine(Long id) {
        Machine machine
                =machineRepository.findById(id).orElseThrow();
        machineRepository.delete(machine);
        logger.info("Machine whit id:"+id+" has bean deleted from the repository");
    }
}
