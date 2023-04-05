package com.diploma.TicketSystem.servicing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/service")
public class ServiceControler {
    private final ServiceService serviceService;
    @Autowired
    public ServiceControler(ServiceService serviceService){this.serviceService=serviceService;}

    @GetMapping
    public List<Service> getService(){
        return serviceService.getService();
    }

    @PostMapping
    public void registerNewService(@RequestBody Service service){
        serviceService.addNewService(service);
    }

    @PutMapping(path="{ticketId}")
    public void updateService(@PathVariable("serviceName")String name,
                              @RequestBody Service service){
        serviceService.updateService(name,service);
    }
}
