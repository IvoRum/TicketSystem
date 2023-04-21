package com.diploma.ticket.system.service;

import com.diploma.ticket.system.util.JwtUtil;
import com.diploma.ticket.system.entity.domain.dto.AuthenticationRequest;
import com.diploma.ticket.system.entity.domain.dto.AuthenticationResponse;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@ReadingConverter
@RequiredArgsConstructor
public class AuthentikationService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;


    @Transactional
    public void register(User request) {
        User userop= userRepository.findByEmail(request.getEmail()).orElseThrow(
                ()->new IllegalStateException("User whit email " + request.getEmail() + " does not exost")
        );
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                                request.getPassword()
                        )
        );
        var user = userRepository.findByEmail(request.getEmail());
        var jwtToken = jwtUtil.generateToken(user.get());
        var refreshToken = jwtUtil.generateRefreshToken(user.get());


        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}

