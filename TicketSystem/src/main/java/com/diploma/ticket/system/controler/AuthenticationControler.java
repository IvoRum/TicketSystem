package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.config.JwtUtil;
import com.diploma.ticket.system.dao.UserDao;
import com.diploma.ticket.system.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthenticationControler {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;//TODO replace whit repository conection
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword())
        );
        final UserDetails user=
                userDao.findUserByEmail(request.getEmail());//TODO REPLACE WHIT REPOSITORY
        if(user !=null){
            return ResponseEntity.ok( jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some Error hase ocured");
    }
}
