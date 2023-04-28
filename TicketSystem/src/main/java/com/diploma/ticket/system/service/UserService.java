package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Transactional
    public void register(User request) {
        User userop= userRepository.findByEmail(request.getEmail()).orElseThrow(
                ()->new IllegalStateException("User whit email " + request.getEmail() + " does not exost")
        );
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);
    }
    @Transactional
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    //Todo add inpleementaiton
    public void addCounter(Long counterId, String authHeader) {

    }
    //Todo add implementaion
    public void addFavorType(Long favorTypeId, String authHeader) {

    }

    public void deleteUser(Integer id) {
        User user
                =userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
}
