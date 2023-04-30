package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Queue;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QueueService {

    private final CounterService counterService;
    private Queue queue;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public QueueService(CounterService counterService, JwtUtil jwtUtil, UserService userService) {
        this.counterService = counterService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        queue=new Queue();
    }

    public void openCounter(Long counterId, String authHeader) {
        Counter counter=
                counterService.findCounter(counterId);
        counter.setActive(true);

        User user=getUserFromToken(authHeader);

        queue.addCounter(user,counter);
    }

    public void closeCounter(Long counterId, String authHeader) {
        Counter counter
                =counterService.findCounter(counterId);
        counter.setActive(false);

        User user=getUserFromToken(authHeader);

        queue.removeCounter(user,counter);
    }


    private User getUserFromToken(String authHeader){
        final String jwtToken;
        jwtToken = authHeader.substring(7);
        String userEmail = jwtUtil.extractUsername(jwtToken);
        User user=userService.findUserByEmail(userEmail);
        return user;
    }

    public PersonalTicket getNextInLineByCounter(Long counterId) {
        Counter counter
                =counterService.findCounter(counterId);
        //get the tickets service
        //find the ticket that is next
        //return the ticket that is next
        return null;
    }

    public Integer getWaitingInLineForCounter(Long counterId) {

        return 0;
    }
}
