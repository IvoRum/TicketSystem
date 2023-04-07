package com.diploma.TicketSystem.conuter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/counter")
public class CounteControler {

    private final CounterService counterService;

    @Autowired
    public CounteControler(CounterService counterService){
        this.counterService=counterService;
    }

    @GetMapping
    public List<Counter> getCounter(){
        return counterService.getCounters();
    }

    @PostMapping
    public void registerNewCounter(@RequestBody Counter counter){
        counterService.addNewCounter(counter);
    }

    @PutMapping(path="{counterId}")
    public void updateCounter(@PathVariable("counterName")String name,
                              @RequestBody Counter counter){
        counterService.updateCounter(name,counter);
    }

}
