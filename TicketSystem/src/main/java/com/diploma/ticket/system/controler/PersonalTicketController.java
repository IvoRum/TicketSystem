package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.exception.NotFountInRepositoryException;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.service.PersonalTicketService;
import com.diploma.ticket.system.entity.PersonalTicket;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
        List<PersonalTicket> responseBody=
                personalTicketService.getPersonaTickets();

        return  ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<CreationResponse> registerPersonalTicket(
            @RequestBody PersonalTicket personalTicket
    ){
        CreationResponse response
                =personalTicketService.addNewPersonalTicket(personalTicket);

        return ResponseEntity.created(URI.create("PersonaTicket")).body(response);
    }

    @PutMapping("/finish/{ticketNumber}")
    public ResponseEntity finishTicket(
            @PathVariable Long ticketNumber,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ){
        CreationResponse responseBody=
                    personalTicketService.finishTicket(ticketNumber,authHeader);
        return ResponseEntity.ok(responseBody);
    }


    @PatchMapping(path="{personalTicketId}")
    public ResponseEntity updateTicket(@PathVariable("ticketNumber")Long number,
                             @RequestBody PersonalTicket personalTicket)
    {
        personalTicketService.updatePersonalTicket(number,personalTicket);
        return ResponseEntity.ok("Ticket whit number:"+number+" hase been updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonalTicket(
            @PathVariable Long id
    ) {
        personalTicketService.deletePersonalTicket(id);
        return ResponseEntity.ok().build();
    }
}
