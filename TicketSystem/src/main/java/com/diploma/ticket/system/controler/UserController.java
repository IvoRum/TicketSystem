package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

    @PutMapping("/addCounter/{counterId}")
    public ResponseEntity addCounter (
            @PathVariable Long counterId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ){
        userService.addCounter(counterId,authHeader);
        return ResponseEntity.ok("Added sucsesfully");
    }

    @PutMapping("/favorType/{favorTypeId}")
    public ResponseEntity addFavorType (
            @PathVariable Long favorTypeId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader
    ){
        userService.addFavorType(favorTypeId,authHeader);

        return ResponseEntity.ok("Added Favor type");
    }
    //TODO add a patch method


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Integer id
    ) {
        userService.deleteUser(id);

        return ResponseEntity.ok("User whit ID:"+id+"");
    }
}
