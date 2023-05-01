package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.payload.response.NextInLineResponse;
import com.diploma.ticket.system.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="api/v1/queue")
public class QueueController {
    private final QueueService queueService;
    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PutMapping("/open/counter/{counterId}")
    public ResponseEntity<?> openCounter(
            @PathVariable Long counterId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ){
        queueService.openCounter(counterId,authHeader);
        return ResponseEntity.ok("Counter whit id:"+counterId+" was opened");
    }

    @DeleteMapping("/close/counter/{counterId}")
    public ResponseEntity<String> closeCounter(
            @PathVariable Long counterId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ){
        queueService.closeCounter(counterId,authHeader);
        return ResponseEntity.ok("Counter whit id:"+counterId+"was closed");
    }

    @GetMapping("/nextInLine/{counterId}")
    public ResponseEntity<NextInLineResponse> getNExtInLine(
            @PathVariable Long counterId
    ){
        PersonalTicket nextInLineTicket
                =queueService.getNextInLineByCounter(counterId);
        if(nextInLineTicket==null){
            return ResponseEntity.notFound().build();
        }

        Integer ticketsForTheCounterWaiting
                =queueService.getWaitingInLineForCounter(counterId);
        NextInLineResponse response
                = NextInLineResponse.builder()
                .number(nextInLineTicket.getNumber())
                .issueTime(nextInLineTicket.getIssueTime())
                .finishTime(nextInLineTicket.getFinishTime())
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/waiting ForCounter/{counterId}")
    public Set<PersonalTicket> getwaithingForCounter(
            @PathVariable Long counterId
    ){
        return queueService.getWaithingForCounter(counterId);
    }

}