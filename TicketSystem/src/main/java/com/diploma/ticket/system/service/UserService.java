package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CounterService counterService;


    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CounterService counterService){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
        this.counterService = counterService;
    }

    @Transactional
    public void register(User request) {
        Optional<User> userop= userRepository.findByEmail(request.getEmail());
        if(userop.isPresent()){
            throw new IllegalArgumentException("User whit email:"+request.getEmail()+"Exists! Try a different email.");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);
    }
    @Transactional
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addCounter(Long counterId, String email) {
        User user=findUserByEmail(email);
        Counter counter=counterService.findCounter(counterId);
        user.addCounter(counter);
    }


    public void deleteUser(Integer id) {
        User user
                =userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void updateUser(Integer id, User user) {
        User userToUpdate=userRepository.findById(id).orElseThrow();
        user.setId(id);
        userRepository.save(user);
    }
}
