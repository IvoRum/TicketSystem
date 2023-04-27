package com.diploma.ticket.system.controler;

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
        try{
            responseBody=counterService.getCounters();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewCounter(@RequestBody Counter counter){
        CreationResponse response=null;
        try{
            response=counterService.addNewCounter(counter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("Counter"))
                .body(response);
    }

    @PostMapping("/add/ticket/{favorId}/{counterId}")
    public ResponseEntity addFavorToCounter(
            @PathVariable Long favorId,
            @PathVariable Long counterId
    ){
        CreationResponse response=null;
        try{
            response=
                    counterService.addNewFavor(favorId,counterId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(response);
    }
    //TODO make bether
    @PatchMapping(path="{counterId}")
    public ResponseEntity updateCounter(@PathVariable("counterName")String name,
                              @RequestBody Counter counter){
        try {
            counterService.updateCounter(name, counter);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCounter(
            @PathVariable Long id
    ) {
        try{
            counterService.deleteCounter(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
