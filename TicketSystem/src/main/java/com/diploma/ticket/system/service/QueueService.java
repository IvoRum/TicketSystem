package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.*;
import com.diploma.ticket.system.payload.response.NextInLineResponse;
import com.diploma.ticket.system.util.JwtUtil;
import org.apache.log4j.Logger;
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
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private static Logger logger= Logger.getLogger(QueueService.class.getName());

    @Autowired
    public QueueService(
            CounterService counterService,
            FavorService favorService,
            TicketService ticketService,
            PersonalTicketService personalTicketService,
            JwtUtil jwtUtil,
            UserService userService
    ) {
        this.counterService = counterService;
        this.favorService = favorService;
        this.ticketService = ticketService;
        this.personalTicketService = personalTicketService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    public void openCounter(Long counterId, String email) {
        Counter counter=
                counterService.findCounter(counterId);
        counter.setActive(true);
        logger.info("Counter whit Id:"+counterId+" was opened");
        User user=getUserFromToken(email);
        //TODO Think a way to integrite to filter
    }

    public void closeCounter(Long counterId, String email) {
        Counter counter
                =counterService.findCounter(counterId);
        counter.setActive(false);
        logger.info("Counter whit Id:"+counterId+" was closed");
        User user=getUserFromToken(email);
        //TODO Think a way to integrite to filter
    }


    private User getUserFromToken(String userEmail){
        User user=userService.findUserByEmail(userEmail);
        logger.info("Get user from Token was invoked");
        return user;
    }

    public Set<PersonalTicket> getWaithingForCounter(Long counterId){
        //1. get the Counter entity
        Counter counter =counterService.findCounter(counterId);
        //2. get the Set of Favor types for the counter
        Set<Favor> favors = counter.getFavor();
        //3. get the tickets for counter
        List<List<Ticket>> ticketInLine=getTicketFromFavors(favors);
        //4. get the personal tickets for the counter
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

    public NextInLineResponse getNextInLineByCounter(
            Long counterId,
            String authHeader
    )  {
        if(getWaithingForCounter(counterId).isEmpty()){
            return new NextInLineResponse(0L,null,null,0);
        }
        //1. get the Counter entity
        Counter counter =counterService.findCounter(counterId);
        //2. get the Set of Favor types for the counter
        Set<Favor> favors = counter.getFavor();
        //3. get the tickets for counter
        List<List<Ticket>> ticketInLine=getTicketFromFavors(favors);
        //4. get the personal tickets for the counter
        List<Set<PersonalTicket>> personalTickets=new ArrayList<>();
        for(Favor favor:favors){
            ticketInLine.add(ticketService.findTicketByFavor(favor.getId()));
        }

        for (List<Ticket> ticketList:ticketInLine){
            for (Ticket ticket:ticketList){
                personalTickets.add(ticket.getPersonalTickets());
            }
        }


        Integer waitingInLine=-1;
        PersonalTicket nextInLine=new PersonalTicket();
        nextInLine.setIssueTime(new Time(23,59,59));
        SetInListIterator<PersonalTicket> nextInLineIterator=new SetInListIterator<>(personalTickets);
        while(nextInLineIterator.hasNext()){
            PersonalTicket next=nextInLineIterator.next();
            if(next.getIssueTime().before(nextInLine.getIssueTime())&&next.isActive()&&next.getFinishTime()==null){
                nextInLine=next;
            }
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

    private List<List<Ticket>> getTicketFromFavors(Set<Favor> favors){
        List<List<Ticket>> ticketInLine=new ArrayList<>();
        for(Favor favor:favors){
            ticketInLine.add(ticketService.findTicketByFavor(favor.getId()));
        }
        logger.info("Get Ticket from Favor was invoked");
        return ticketInLine;
    }

    private List<Set<PersonalTicket>> getPersonalTicetsFromTicket(List<List<Ticket>> ticketInLine)  {
        List<Set<PersonalTicket>> personalTickets = new ArrayList<>();
        for (List<Ticket> ticketList : ticketInLine) {
            for (Ticket ticket : ticketList) {
                personalTickets.add(ticket.getPersonalTickets());
            }
        }
        return personalTickets;
    }

    /**
     * The class is used for the way in witch the line filters out the next ticket
     * @param <T> The set of Personal Tickets
     */
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

