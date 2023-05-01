package com.diploma.ticket.system.service;

import com.diploma.ticket.system.util.JwtUtil;
import com.diploma.ticket.system.payload.request.AuthenticationRequest;
import com.diploma.ticket.system.payload.response.AuthenticationResponse;
import com.diploma.ticket.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

        var jwtToken = jwtUtil.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}

