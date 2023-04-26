package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Machine;
import com.diploma.ticket.system.payload.request.MachineCreationRequest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
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
    public ResponseEntity<List<Machine>> getMachines(){
        List<Machine> responseBody=new ArrayList<>();
        try{
            responseBody=machineService.getMachines();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewMachine(
            @RequestBody MachineCreationRequest machine
    )
    {
        CreationResponse responseBody=null;
       // try{
            responseBody= machineService.addNewMachine(machine);
       // } catch (Exception e) {
           // return ResponseEntity.badRequest().build();
       // }
        return ResponseEntity.created(URI.create("Machine"))
                .body(responseBody);
    }

    @PutMapping(path="{machineName}")
    public void updateMachine(@PathVariable("machineName")String name,
                             @RequestBody Machine machine){
        machineService.updateMachine(name,machine);
    }
}