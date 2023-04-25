package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.PersonalTicketService;
import com.diploma.ticket.system.entity.PersonalTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/v2/personalticket")
public class PersonalTicketController {
    private final PersonalTicketService personalTicketService;

    @Autowired
    public PersonalTicketController(PersonalTicketService personalTicketService){
        this.personalTicketService=personalTicketService;
    }
    @GetMapping
    public ResponseEntity<List<PersonalTicket>> getTickets(){
        List<PersonalTicket> responseBody=new ArrayList<>();
        try{
            responseBody
                    = personalTicketService.getPersonaTickets();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerPersonalTicket(
            @RequestBody PersonalTicket personalTicket
    ){
        CreationResponse response=null;
        try{
            response=personalTicketService.addNewPersonalTicket(personalTicket);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("PersonaTicket")).body(response);
    }

    @PostMapping("/finish/{ticketNumber}")
    public ResponseEntity finishTicket(
            @PathVariable Long ticketNumber
    ){
        CreationResponse responseBody=null;
        try{
            responseBody=
                    personalTicketService.finishTicket(ticketNumber);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responseBody);
    }


    @PutMapping(path="{personalTicketId}")
    public void updateTicket(@PathVariable("ticketNumber")Long number,
                             @RequestBody PersonalTicket personalTicket){
        personalTicketService.updatePersonalTicket(number,personalTicket);
    }
}
