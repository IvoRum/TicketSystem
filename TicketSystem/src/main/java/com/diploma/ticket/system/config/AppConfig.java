package com.diploma.ticket.system.config;

import com.diploma.ticket.system.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
    @Bean
    public static JwtUtil jwtUtilConfig() {
        return new JwtUtil();
    }
}
