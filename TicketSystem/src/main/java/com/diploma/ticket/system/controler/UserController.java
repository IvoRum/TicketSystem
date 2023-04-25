package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(
            @RequestBody User request
    ) {
        try {
            userService.register(request);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST
            );
        }
    }
    @PostMapping("/addCounter/{counterId}")
    public ResponseEntity addCounter (
            @PathVariable Long counterId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ){
        try {
            userService.addCounter(counterId,authHeader);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Added sucsesfully");
    }

    @PostMapping("/favorType/{favorTypeId}")
    public ResponseEntity addFavorType (
            @PathVariable Long favorTypeId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ){
        try {
            userService.addFavorType(favorTypeId,authHeader);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Added Favor type");
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> responseBody=new ArrayList<>();
        try{
            responseBody=userService.getUsers();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(responseBody);
    }

}
