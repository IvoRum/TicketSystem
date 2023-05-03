package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.repository.UserRepository;
import org.apache.log4j.Logger;
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
    private static Logger logger= Logger.getLogger(TicketTypeService.class.getName());

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
            logger.info("User type whit Name:"+request.getEmail()+" Exists!");
            throw new IllegalArgumentException("User whit email:"+request.getEmail()+"Exists! Try a different email.");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);
        logger.info("User type whit Name:"+request.getEmail()+" has bean saved to the repository");
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addCounter(Long counterId, String email) {
        User user=findUserByEmail(email);
        Counter counter=counterService.findCounter(counterId);
        user.addCounter(counter);
        logger.info(
                "Add counter method was invoked whit the CounterId:" +counterId+
                " And whit the user email:"+email
                );
    }


    public void deleteUser(Integer id) {
        User user
                =userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        logger.info("User type whit Id:"+id+" has bean saved to the repository");
    }

    public User findUserByEmail(String email){
        logger.info("Find User by Email was invoked.");
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void updateUser(Integer id, User user) {
        User userToUpdate=userRepository.findById(id).orElseThrow();
        user.setId(id);
        userRepository.save(user);
        logger.info("User whit id:"+id+"was successfully updated and saved to the repository");
    }
}
