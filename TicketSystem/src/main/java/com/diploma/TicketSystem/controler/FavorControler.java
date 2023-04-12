package com.diploma.TicketSystem.controler;

import com.diploma.TicketSystem.entity.Favor;
import com.diploma.TicketSystem.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/service")
public class FavorControler {
    private final FavorService favorService;
    @Autowired
    public FavorControler(FavorService favorService){this.favorService = favorService;}

    @GetMapping
    public List<Favor> getService(){
        return favorService.getService();
    }

    @PostMapping
    public void registerNewService(@RequestBody Favor favor){
        favorService.addNewService(favor);
    }

    @PutMapping(path="{ticketId}")
    public void updateService(@PathVariable("serviceName")String name,
                              @RequestBody Favor favor){
        favorService.updateService(name, favor);
    }
}
