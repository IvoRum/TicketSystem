package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.*;
import com.diploma.ticket.system.payload.response.NextInLineResponse;
import com.diploma.ticket.system.util.JwtUtil;
import com.diploma.ticket.system.util.QueueUtil;
import com.diploma.ticket.system.util.WaitingForCounter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;

@Service
@Transactional
public class QueueService {

    private final CounterService counterService;
    private final FavorService favorService;
    private final TicketService ticketService;
    private final PersonalTicketService personalTicketService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private static Logger logger= Logger.getLogger(QueueService.class.getName());

    private final QueueUtil queueUtil;

    static Semaphore semaphore = new Semaphore(10);
    private ExecutorService executorService= Executors.newFixedThreadPool(10);;

    @Autowired
    public QueueService(
            CounterService counterService,
            FavorService favorService,
            TicketService ticketService,
            PersonalTicketService personalTicketService,
            JwtUtil jwtUtil,
            UserService userService,
            QueueUtil queueUtil) {
        this.counterService = counterService;
        this.favorService = favorService;
        this.ticketService = ticketService;
        this.personalTicketService = personalTicketService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.queueUtil = queueUtil;
    }

    public void openCounter(Long counterId, String email) {
        Counter counter=
                counterService.findCounter(counterId);
        counter.setActive(true);
        logger.info("Counter whit Id:"+counterId+" was opened");
        User user=queueUtil.getUserFromToken(email);
        //TODO Think a way to integrate to filter
    }

    public void closeCounter(Long counterId, String email) {
        Counter counter
                =counterService.findCounter(counterId);
        counter.setActive(false);
        logger.info("Counter whit Id:"+counterId+" was closed");
        User user=queueUtil.getUserFromToken(email);
        //TODO Think a way to integrate to filter
    }


    public Set<PersonalTicket> getWaitingForCounter(Long counterId){
        WaitingForCounter waitingForCounter=new WaitingForCounter(counterService,counterId,ticketService);
        if (semaphore.tryAcquire()) {
            try {
                waitingForCounter.run();
            }
            finally {
                semaphore.release();
            }
        }
        return waitingForCounter.getWaithingTickets();
    }

    public NextInLineResponse getNextInLineByCounter(
            Long counterId,
            String authHeader
    )  {
        Counter counter =counterService.findCounter(counterId);
        Set<Favor> favors = counter.getFavor();
        List<List<Ticket>> ticketInLine=queueUtil.getTicketFromFavors(favors);
        List<Set<PersonalTicket>> personalTickets= queueUtil.getPersonalTicketsFromTicket(ticketInLine);

        Integer waitingInLine=-1;
        PersonalTicket nextInLine=new PersonalTicket();
        nextInLine.setIssueTime(new Time(23,59,59));
        QueueUtil.SetInListIterator<PersonalTicket> nextInLineIterator=new QueueUtil.SetInListIterator<>(personalTickets);
        while(nextInLineIterator.hasNext()){
            PersonalTicket next=nextInLineIterator.next();
            if(next.getIssueTime().before(nextInLine.getIssueTime())&&next.isActive()&&next.getFinishTime()==null){
                waitingInLine++;
                nextInLine=next;
            }
        }
        if(nextInLine.getId()==null){
            return null;
        }

        personalTicketService.setTicketToUser(authHeader,nextInLine);

        NextInLineResponse response=
                NextInLineResponse
                        .builder()
                        .number(nextInLine.getId())
                        .issueTime(nextInLine.getIssueTime())
                        .finishTime(nextInLine.getFinishTime())
                        .peopleInLine(waitingInLine)
                        .build();

        return response;
    }

}

