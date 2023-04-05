package com.diploma.TicketSystem.Ticketing.Ticket;

import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
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
                TicketType.TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();

        listOfTypes.add(type);

        underTest.saveAllAndFlush(List.of(
                new Ticket("Name1",listOfTypes),
                new Ticket("Name2",listOfTypes),
                new Ticket("Name3",listOfTypes)
        ));

        //when
        Optional<Ticket> exists=underTest.findByTicketName("Name1");

        //then
        assertThat(exists.isPresent()).isTrue();

    }

    @Test
    void itShouldCheckIfTicketfindByTicketNumberDoseNotExists() {
        //given
        TicketType type=new
                TicketType.TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();

        listOfTypes.add(type);

        underTest.saveAllAndFlush(List.of(
                new Ticket("Name1",listOfTypes),
                new Ticket("Name2",listOfTypes),
                new Ticket("Name3",listOfTypes)
        ));

        //when
        Optional<Ticket> exists=underTest.findByTicketName("Name4");

        //then
        assertThat(exists.isPresent()).isFalse();

    }
}