package com.diploma.ticket.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
}
