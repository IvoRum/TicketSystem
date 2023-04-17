package com.diploma.ticket.system.service;

import com.diploma.ticket.system.config.JwtUtil;
import com.diploma.ticket.system.domain.dto.AuthenticationRequest;
import com.diploma.ticket.system.domain.dto.CreateUserRequest;
import com.diploma.ticket.system.domain.dto.UserView;
import com.diploma.ticket.system.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@ReadingConverter
@RequiredArgsConstructor
public class AuthentikationService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity authenticat(AuthenticationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),request.password())
        );
        final UserDetails user=
                userRepository.findByEmail(request.username());
        if(user !=null){
            return ResponseEntity.ok( jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some Error hase ocured");

    }
}
