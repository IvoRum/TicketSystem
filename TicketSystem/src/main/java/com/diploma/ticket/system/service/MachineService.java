package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Machine;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MachineService {
    private final MachineRepository machineRepository;
    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }



    public List<Machine> getMachines() {
        return machineRepository.findAll();
    }

    public void addNewMachine(Machine machine) {
        Optional<Machine> machineOptional
                =machineRepository.findMachineByName(machine.getName());
        boolean exists=machineOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }
        machineRepository.save(machine);
    }

    public void updateMachine(String nameOfMachineToUpdate, Machine machine) {
        String name=machine.getName();
        Optional<Machine> updatedMachine=machineRepository.findMachineByName(nameOfMachineToUpdate);
        boolean exits=updatedMachine.isPresent();
        if(exits) {
            new IllegalStateException("machine whit name " + nameOfMachineToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(machine.getName(),name)){
            updatedMachine.get().setName(name);
        }
        machineRepository.save(machine);
    }
}
