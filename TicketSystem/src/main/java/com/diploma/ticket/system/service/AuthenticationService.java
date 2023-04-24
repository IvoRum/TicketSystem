package com.diploma.ticket.system.service;

import com.diploma.ticket.system.type.Role;
import com.diploma.ticket.system.util.JwtUtil;
import com.diploma.ticket.system.payload.request.AuthenticationRequest;
import com.diploma.ticket.system.payload.response.AuthenticationResponse;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@Transactional(readOnly = true)
@ReadingConverter
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                                request.getPassword()
                        )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "user not found"
                )
        );

        String username= user.getUsername();
        String email=user.getEmail();
        Role role=user.getRole();
        var jwtToken = jwtUtil.generateToken(user);
        var refreshToken = jwtUtil.generateRefreshToken(user);


        return AuthenticationResponse.builder()
                .email(email)
                .roles(role)
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}

