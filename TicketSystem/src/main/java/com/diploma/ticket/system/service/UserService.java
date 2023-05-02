package com.diploma.ticket.system.service;

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


    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void updateUser(Integer id, User user) {
        User userToUpdate=userRepository.findById(id).orElseThrow();
        user.setId(id);
        userRepository.save(user);
    }
}
