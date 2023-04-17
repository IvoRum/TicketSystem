package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.config.JwtUtil;
import com.diploma.ticket.system.domain.dto.AuthenticationRequest;
import com.diploma.ticket.system.domain.dto.CreateUserRequest;
import com.diploma.ticket.system.domain.dto.UserView;
import com.diploma.ticket.system.service.AuthentikationService;
import com.diploma.ticket.system.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthenticationControler {


    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private final AuthentikationService authentikationService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return authentikationService.authenticat(request);
     }
    @PostMapping("register")
    public UserView register(@RequestBody @Valid CreateUserRequest request) {
        return authentikationService.create(request);
    }
}
