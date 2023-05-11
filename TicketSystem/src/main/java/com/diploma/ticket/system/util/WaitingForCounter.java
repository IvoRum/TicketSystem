package com.diploma.ticket.system.util;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.service.CounterService;
import com.diploma.ticket.system.service.QueueService;
import com.diploma.ticket.system.service.TicketService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WaitingForCounter implements Runnable{

    private Set<PersonalTicket> waithingTickets=new HashSet<>();
    private final CounterService counterService;
    private final Long counterId;
    private final TicketService ticketService;

    public WaitingForCounter(CounterService counterService, Long counterId, TicketService ticketService) {
        this.counterService = counterService;
        this.counterId = counterId;
        this.ticketService = ticketService;
    }

    @Override
    public void run()
    {
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

        QueueService.SetInListIterator<PersonalTicket> nextInLineIterator=new QueueService.SetInListIterator<>(personalTickets);
        while(nextInLineIterator.hasNext()){
            PersonalTicket next=nextInLineIterator.next();
            if(next.isActive()&&next.getFinishTime()==null){
                waithingTickets.add(next);
            }
        }
    }

    private List<List<Ticket>> getTicketFromFavors(Set<Favor> favors) {
        List<List<Ticket>> ticketInLine=new ArrayList<>();
        for(Favor favor:favors){
            ticketInLine.add(ticketService.findTicketByFavor(favor.getId()));
        }
        return ticketInLine;
    }

    public Set<PersonalTicket> getWaithingTickets() {
        return this.waithingTickets;
    }
}
