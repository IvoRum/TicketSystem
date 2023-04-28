package com.diploma.ticket.system.entity;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Queue {
    private List<Counter> counters;
    private List<Favor> filters;
    private List<PersonalTicket> tickets;

    public void orderQueue(){}
}
