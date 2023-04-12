package com.diploma.TicketSystem.controler;

import com.diploma.TicketSystem.entity.FavorType;
import com.diploma.TicketSystem.service.FavorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/servicetype")
public class FavorTypeControler {


    private final FavorTypeService favorTypeService;

    @Autowired
    public FavorTypeControler(FavorTypeService favorTypeService){

        this.favorTypeService = favorTypeService;
    }

    @GetMapping
    public List<FavorType> getServiceTypes(){

        return favorTypeService.getServiceTypes();
    }

    @PostMapping
    public void registerNewServiceType(@RequestBody FavorType favorType){
        favorTypeService.addNewServiceType(favorType);
    }

    @PutMapping(path="{serviceTypeId}")
    public void updateTicket(@PathVariable("serviceTypName")String nameOfServiceTypToUpdate,
                             @RequestBody FavorType favorType){
        favorTypeService.updateTicket(nameOfServiceTypToUpdate, favorType);
    }
}
