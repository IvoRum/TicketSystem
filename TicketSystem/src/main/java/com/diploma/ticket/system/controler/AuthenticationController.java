package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.util.JwtUtil;
import com.diploma.ticket.system.entity.domain.dto.AuthenticationRequest;
import com.diploma.ticket.system.entity.domain.dto.AuthenticationResponse;
import com.diploma.ticket.system.entity.domain.dto.RegisterRequest;
import com.diploma.ticket.system.service.AuthentikationService;
import com.diploma.ticket.system.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthentikationService authentikationService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authentikationService.authenticate(request));
    }
    @PostMapping("/register")
    public void register(
            @RequestBody User request
    ) {
        try {
            authentikationService.register(request);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
