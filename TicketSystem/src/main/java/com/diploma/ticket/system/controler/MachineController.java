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
        responseBody=machineService.getMachines();

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewMachine(
            @RequestBody MachineCreationRequest machine
    )
    {
        CreationResponse responseBody=null;
        responseBody= machineService.addNewMachine(machine);
        return ResponseEntity.created(URI.create("Machine"))
                .body(responseBody);
    }

    @PutMapping(path="{machineName}")
    public void updateMachine(@PathVariable("machineName")String name,
                             @RequestBody Machine machine){
        machineService.updateMachine(name,machine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMachine(
            @PathVariable Long id
    ) {
        machineService.deleteMachine(id);

        return ResponseEntity.ok("Machina whit ID:"+id+"was deleted successfully.");
    }
}