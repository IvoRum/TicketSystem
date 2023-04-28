package com.diploma.ticket.system.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Queue {
    private List<Counter> counters;
    private List<Favor> filters;
    private List<PersonalTicket> tickets;

    public void orderQueue(){}
}
