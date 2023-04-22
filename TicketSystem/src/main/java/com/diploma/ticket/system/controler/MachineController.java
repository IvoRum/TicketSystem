package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Machine;
import com.diploma.ticket.system.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/machine")
public class MachineController {

    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService service){
        this.machineService=service;
    }

    @GetMapping
    public List<Machine> getMachines(){
        return machineService.getMachines();
    }

    @PostMapping
    public void registerNewMachine(@RequestBody Machine machine)
    {
        machineService.addNewMachine(machine);
    }

    @PutMapping(path="{machineId}")
    public void updateMachine(@PathVariable("machineName")String name,
                             @RequestBody Machine machine){
        machineService.updateMachine(name,machine);
    }
}