package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
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

    @GetMapping("/authentication")
    public Object getAuthentication(@CurrentSecurityContext(expression = "authentication")
                                    Authentication authentication) {
        return authentication.getName();
    }

    @PostMapping("/register")
    public void register(
            @RequestBody User request
    ) {
        userService.register(request);
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
    @PatchMapping(path="{userId}")
    public ResponseEntity updateService(
            @PathVariable("userId")Integer id,
            @RequestBody User user
    ){

        userService.updateUser(id, user);
        return ResponseEntity.ok("User whit id:"+id+"hase bean updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Integer id
    ) {
        userService.deleteUser(id);

        return ResponseEntity.ok("User whit ID:"+id+"");
    }
}
