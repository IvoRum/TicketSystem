package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
