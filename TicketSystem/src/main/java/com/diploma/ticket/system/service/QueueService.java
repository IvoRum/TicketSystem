package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.*;
import com.diploma.ticket.system.entity.Queue;
import com.diploma.ticket.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.*;

@Service
@Transactional
public class QueueService {

    private final CounterService counterService;
    private final FavorService favorService;
    private final TicketService ticketService;
    private final PersonalTicketService personalTicketService;
    private Queue queue;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public QueueService(CounterService counterService, FavorService favorService, TicketService ticketService, PersonalTicketService personalTicketService, JwtUtil jwtUtil, UserService userService) {
        this.counterService = counterService;
        this.favorService = favorService;
        this.ticketService = ticketService;
        this.personalTicketService = personalTicketService;
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

    public Set<PersonalTicket> getWaithingForCounter(Long counterId){
        //1. get the Counter entity

        Counter counter
                =counterService.findCounter(counterId);
        //2. get the Set of Favor types for the counter
        Set<Favor> favors
                = counter.getFavor();

        //4. get the tickets for counter
        List<List<Ticket>> ticketInLine=new ArrayList<>();
        for(Favor favor:favors){
            ticketInLine.add(ticketService.findTicketByFavor(favor.getId()));
        }
        //5. get the personal tickets for the counter
        List<Set<PersonalTicket>> personalTickets=new ArrayList<>();
        for (List<Ticket> ticketList:ticketInLine){
            for (Ticket ticket:ticketList){
                personalTickets.add(ticket.getPersonalTickets());
            }
        }
        Set<PersonalTicket> waithingTickets=new HashSet<>();
        SetInListIterator<PersonalTicket> nextInLineIterator=new SetInListIterator<>(personalTickets);
        while(nextInLineIterator.hasNext()){
            PersonalTicket next=nextInLineIterator.next();
            if(next.isActive()&&next.getFinishTime()==null){
                waithingTickets.add(next);
            }
        }


        return waithingTickets;
    }
    public PersonalTicket getNextInLineByCounter(Long counterId) {
        //1. get the Counter entity

        Counter counter
                =counterService.findCounter(counterId);
        //2. get the Set of Favor types for the counter
        Set<Favor> favors
                = counter.getFavor();

        //4. get the tickets for counter
        List<List<Ticket>> ticketInLine=new ArrayList<>();
        for(Favor favor:favors){
            ticketInLine.add(ticketService.findTicketByFavor(favor.getId()));
        }
        //5. get the personal tickets for the counter
        List<Set<PersonalTicket>> personalTickets=new ArrayList<>();
        for (List<Ticket> ticketList:ticketInLine){
            for (Ticket ticket:ticketList){
                personalTickets.add(ticket.getPersonalTickets());
            }
        }

        //6. return the next in line
        PersonalTicket nextInLine=new PersonalTicket();
        nextInLine.setIssueTime(new Time(23,59,59));
        SetInListIterator<PersonalTicket> nextInLineIterator=new SetInListIterator<>(personalTickets);
        while(nextInLineIterator.hasNext()){
            PersonalTicket next=nextInLineIterator.next();
            if(next.getIssueTime().before(nextInLine.getIssueTime())&&next.isActive()&&next.getFinishTime()==null){
                nextInLine=next;
            }
        }
        if(nextInLine.getNumber()==null){
            return null;
        }
        return nextInLine;
    }


    public Integer getWaitingInLineForCounter(Long counterId) {

        return 1;
    }


    public class SetInListIterator<T> implements Iterator<T> {

        private final List<Set<T>> list;
        private int currentListIndex;
        private Iterator<T> currentSetIterator;

        public SetInListIterator(List<Set<T>> list) {
            this.list = list;
            this.currentListIndex = 0;
            this.currentSetIterator = list.get(0).iterator();
        }

        @Override
        public boolean hasNext() {
            if (currentSetIterator.hasNext()) {
                return true;
            } else if (currentListIndex < list.size() - 1) {
                currentListIndex++;
                currentSetIterator = list.get(currentListIndex).iterator();
                return hasNext();
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            if (hasNext()) {
                return currentSetIterator.next();
            } else {
                throw new java.util.NoSuchElementException();
            }
        }

        public Iterator<T> getCurrent(){
            return currentSetIterator;
        }
    }
}
