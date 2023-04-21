package com.diploma.ticket.system.initialization;

import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.entity.domain.Role;
import com.diploma.ticket.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("ivoAdmin@mail.com");
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setRole(Role.USER);
        userRepository.save(user);
    }
}
