package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> closeCounter(
            @PathVariable Long counterId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ){
        return ResponseEntity.ok("Counter whit id:"+counterId+"was closed");
    }

}
