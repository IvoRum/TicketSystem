package com.diploma.TicketSystem.TicketTesting;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import com.diploma.TicketSystem.Ticketing.Ticket.TicketRepository;
import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
import com.diploma.TicketSystem.Ticketing.TicketType.TicketTypeBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TicketCRUDTests {
    @Autowired
    TicketRepository repository;
    @BeforeEach
    void setUp(){
        TicketType type=new
                TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();
        listOfTypes.add(type);

        repository.saveAllAndFlush(List.of(
              new Ticket("DA",(long) 55,listOfTypes),
                new Ticket("DA",(long)66,listOfTypes),
                new Ticket("DA", (long)77,listOfTypes)
        ));
    }

    @Test
    public void findAllShouldReduceAllTickets(){
        List<Ticket> tickets= repository.findAll();
        assertThat(tickets).hasSize(4);
    }

}
