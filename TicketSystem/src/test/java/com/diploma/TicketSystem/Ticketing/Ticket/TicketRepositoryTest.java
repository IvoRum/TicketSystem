package com.diploma.TicketSystem.Ticketing.Ticket;

import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
import com.diploma.TicketSystem.Ticketing.TicketType.TicketTypeBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TicketRepositoryTest {
    @Autowired
    private TicketRepository underTest;
    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void itShouldCheckIfTicketfindByTicketNumberDoseExists() {
        //given
        TicketType type=new
                TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();

        listOfTypes.add(type);

        underTest.saveAllAndFlush(List.of(
                new Ticket("DA1",listOfTypes),
                new Ticket("DA",listOfTypes),
                new Ticket("DA",listOfTypes)
        ));

        //when
        Optional<Ticket> exists=underTest.findByTicketName("DA1");

        //then
        assertThat(exists.isPresent()).isTrue();

    }

    @Test
    void itShouldCheckIfTicketfindByTicketNumberDoseNotExists() {
        //given
        TicketType type=new
                TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();

        listOfTypes.add(type);

        underTest.saveAllAndFlush(List.of(
                new Ticket("DA",listOfTypes),
                new Ticket("DA",listOfTypes),
                new Ticket("DA",listOfTypes)
        ));

        //when
        Optional<Ticket> exists=underTest.findByTicketName("NE");

        //then
        assertThat(exists.isPresent()).isFalse();

    }
}