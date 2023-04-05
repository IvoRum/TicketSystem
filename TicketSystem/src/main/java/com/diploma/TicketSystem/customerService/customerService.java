package com.diploma.TicketSystem.customerService;

import com.diploma.TicketSystem.Ticketing.article.ArticleType;
import com.diploma.TicketSystem.conuter.Counter;

import java.util.List;

public class customerService {
    private String name;
    private String description;
    private long workStart,workEnd;
    private ArticleType articleType;
    private List<Counter> counters;
}
