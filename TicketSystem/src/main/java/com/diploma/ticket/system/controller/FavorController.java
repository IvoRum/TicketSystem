package com.diploma.ticket.system.controller;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.payload.request.FavorCreationReqest;
import com.diploma.ticket.system.payload.request.FavorWhitTicketRequest;
import com.diploma.ticket.system.payload.request.TicketCreationRequest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.payload.response.FavorWhitTicketResponse;
import com.diploma.ticket.system.service.FavorService;
import com.diploma.ticket.system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/v2/favor")
public class FavorController {
    private final FavorService favorService;
    private final TicketService ticketService;
    @Autowired
    public FavorController(FavorService favorService, TicketService ticketService)
    {
        this.favorService = favorService;
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Favor>> getService(){
        List<Favor> responseBody=new ArrayList<>();
        responseBody=favorService.getService();

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerNewService(@RequestBody FavorCreationReqest favor){
        CreationResponse response=null;
        response=favorService.addNewService(favor);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/whitTicket")
    public ResponseEntity<FavorWhitTicketResponse> registerNewServiceWhitTicket(
            @RequestBody FavorWhitTicketRequest request
    ){
        FavorWhitTicketResponse finalResponse;
        CreationResponse favorCreationResponse=null,ticketCreationResponse=null;
        favorCreationResponse=favorService.addNewService(request.getFavorCreationReqest());
        finalResponse=new FavorWhitTicketResponse();
        finalResponse.setFavorId(favorCreationResponse.getId());
        ticketCreationResponse=ticketService.addNewTicket(request.getTicketCreationRequest());
        finalResponse.setTicketId(ticketCreationResponse.getId());
        ticketService.addFavor(ticketCreationResponse.getId(), favorCreationResponse.getId());

        return ResponseEntity.ok(finalResponse);
    }
    @PatchMapping(path="{favorName}")
    public ResponseEntity<String> updateService(
            @PathVariable("favorName")String name,
            @RequestBody Favor favor
    ){

        favorService.updateService(name, favor);
        return ResponseEntity.ok("Favor whit name"+name+"hase bean updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavor(
            @PathVariable Long id
    ) {
        favorService.deleteFavor(id);
        return ResponseEntity.ok().build();
    }
}
