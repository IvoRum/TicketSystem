package com.diploma.TicketSystem.servicing.serviceType;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import com.diploma.TicketSystem.Ticketing.Ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/servicetype")
public class ServiceTypeControler {


    private final ServiceTypeService serviceTypeService;

    @Autowired
    public ServiceTypeControler(ServiceTypeService serviceTypeService){

        this.serviceTypeService=serviceTypeService;
    }

    @GetMapping
    public List<ServiceType> getServiceTypes(){

        return serviceTypeService.getServiceTypes();
    }

    @PostMapping
    public void registerNewServiceType(@RequestBody ServiceType serviceType){
        serviceTypeService.addNewServiceType(serviceType);
    }

    @PutMapping(path="{serviceTypeId}")
    public void updateTicket(@PathVariable("serviceTypName")String nameOfServiceTypToUpdate,
                             @RequestBody ServiceType serviceType){
        serviceTypeService.updateTicket(nameOfServiceTypToUpdate,serviceType);
    }
}
