package com.diploma.ticket.system.entity;

import org.springframework.security.access.method.P;

import java.util.*;
import java.util.stream.Collectors;

public class Queue {
    private Map<User,Counter> counters;
    private List<Favor> filters;
    private Map<Ticket,LinkedList<PersonalTicket>> tickets;

    public Queue(List<Favor> filters, Map<Ticket, LinkedList<PersonalTicket>> tickets) {
        this.filters = filters;
        this.tickets = tickets;
    }

    public Queue() {
        counters=new HashMap<>();
        filters=new LinkedList<>();
        tickets=new TreeMap<>();
    }

    public void addCounter(User user,Counter counter) {
        if(counter.isActive())
            counters.put(user,counter);
    }

    public void removeCounter(User user, Counter counter) {
        counters.remove(user,counter);
    }

    public PersonalTicket getNextTicketByFavorTypes(
            Set<FavorType> FavorTypes
    )
    {
        PersonalTicket ticket;



        return null;
    }
}
