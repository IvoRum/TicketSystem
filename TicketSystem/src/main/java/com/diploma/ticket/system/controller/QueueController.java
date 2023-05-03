package com.diploma.ticket.system.controller;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.payload.response.NextInLineResponse;
import com.diploma.ticket.system.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path="api/v1/queue")
public class QueueController {
    private final QueueService queueService;
    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }
    //ToDo asc how to act whit log in to counter🔥💀
    @GetMapping("/nextInLine/{counterId}")
    public ResponseEntity<NextInLineResponse> getNExtInLine(
            @PathVariable Long counterId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    )  {
        NextInLineResponse response
                =queueService.getNextInLineByCounter(counterId,authHeader);
        if(response==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/authentication")
    public Object getAuthentication(
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
    ) {
        return authentication.getName();
    }
    @PutMapping("/open/counter/{counterId}")
    public ResponseEntity<?> openCounter(
            @PathVariable Long counterId,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
    ){
        queueService.openCounter(counterId,authentication.getName());
        return ResponseEntity.ok("Counter whit id:"+counterId+" was opened");
    }

    @DeleteMapping("/close/counter/{counterId}")
    public ResponseEntity<String> closeCounter(
            @PathVariable Long counterId,
            @CurrentSecurityContext(expression = "authentication") Authentication authentication
    ){
        queueService.closeCounter(counterId,authentication.getName());
        return ResponseEntity.ok("Counter whit id:"+counterId+"was closed");
    }


    @GetMapping("/waiting ForCounter/{counterId}")
    public Set<PersonalTicket> getWaitingForCounter(
            @PathVariable Long counterId
    ){
        return queueService.getWaithingForCounter(counterId);
    }


}
