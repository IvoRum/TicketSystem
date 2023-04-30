package com.diploma.ticket.system.entity;

import java.util.*;

public class Queue {
    private Map<User,Counter> counters;
    private List<Favor> filters;
    private List<PersonalTicket> tickets;

    public Queue() {
        counters=new HashMap<>();
        filters=new LinkedList<>();
        tickets=new ArrayList<>();
    }

    public void addCounter(User user,Counter counter) {
        if(counter.isActive())
            counters.put(user,counter);
    }

    public void removeCounter(User user, Counter counter) {
        counters.remove(user,counter);
    }
}
