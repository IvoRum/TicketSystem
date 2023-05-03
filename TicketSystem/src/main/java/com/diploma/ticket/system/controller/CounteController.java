package com.diploma.ticket.system.controller;

import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.CounterService;
import com.diploma.ticket.system.entity.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/v2/counter")
public class CounteController {

    private final CounterService counterService;

    @Autowired
    public CounteController(CounterService counterService){
        this.counterService=counterService;
    }

    @GetMapping
    public ResponseEntity<List<Counter>> getCounter(){
        List<Counter> responseBody=new ArrayList<>();
        responseBody=counterService.getCounters();

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewCounter(@RequestBody Counter counter){
        CreationResponse response=null;

        response=counterService.addNewCounter(counter);

        return ResponseEntity.created(URI.create("Counter"))
                .body(response);
    }

    @PutMapping("/add/ticket/{favorId}/{counterId}")
    public ResponseEntity addFavorToCounter(
            @PathVariable Long favorId,
            @PathVariable Long counterId
    ){
        CreationResponse response=null;

        counterService.addNewFavor(favorId,counterId);

        return ResponseEntity.ok().body(response);
    }
    @PatchMapping(path="{counterId}")
    public ResponseEntity<String> updateCounter(
            @PathVariable("counterName")String name,
            @RequestBody Counter counter)
    {
        counterService.updateCounter(name, counter);

        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCounter(
            @PathVariable Long id
    ) {
        counterService.deleteCounter(id);

        return ResponseEntity.ok().build();
    }

}
