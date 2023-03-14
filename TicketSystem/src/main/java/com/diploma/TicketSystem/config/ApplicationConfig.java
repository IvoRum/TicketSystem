package com.diploma.TicketSystem.config;


import com.diploma.TicketSystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository repository;


    @Bean
    public UserDetailsService userDetailsService(){
        return username -> (UserDetails) repository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("Userr not foulnd"));
    }
    //data object for user details and much more
    @Bean
    public AuthenticationProvider authenticationProvideer(){
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());//make into a bean
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(ApplicationConfig config){
        return config.authenticationManager(config);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
