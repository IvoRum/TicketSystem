package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TicketRepositoryTest {

    @Autowired
    TicketRepository underTest;
    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByTicketName() {
        //given
        String name="TicketNameEx";
        Ticket ticket= Ticket.builder().name(name).build();
        underTest.save(ticket);
        //when
        boolean expected=underTest.findByTicketName(name).isPresent();

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldNotFindByTicketName() {
        //given
        String name="TicketNameEx";
        String falseName="FalseName";
        Ticket ticket= Ticket.builder().name(name).build();
        underTest.save(ticket);
        //when
        boolean expected=underTest.findByTicketName(falseName).isPresent();

        //then
        assertThat(expected).isFalse();
    }
}