package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/favor")
public class FavorController {
    private final FavorService favorService;
    @Autowired
    public FavorController(FavorService favorService){this.favorService = favorService;}

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
