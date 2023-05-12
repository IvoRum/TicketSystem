package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Machine;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PersonalTicketRepositoryTest {

    @Autowired
    private PersonalTicketRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindPersonalTicketByNUmber() {
        //given
        Long id=1l;
        PersonalTicket personalTicket=new PersonalTicket(id,false,null,null,null);
        underTest.save(personalTicket);
        //when

        boolean expected=underTest.findPersonalTicketByNUmber(id).isPresent();

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldNotFindPersonalTicketByNUmber() {
        //given
        Long id=1l;
        Long falseId=2l;
        PersonalTicket personalTicket=new PersonalTicket(id,false,null,null,null);
        underTest.save(personalTicket);
        //when

        boolean expected=underTest.findPersonalTicketByNUmber(falseId).isPresent();

        //then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldFindPersonalTicketsByTicket() {
        //given
        PersonalTicket personalTicket=new PersonalTicket(1l,false,null,null,null);
        underTest.save(personalTicket);
        //when

        PersonalTicket lastPersonalTicket=underTest.findLastPersonalTicket();
        boolean expected=personalTicket.equals(lastPersonalTicket);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldNotFindPersonalTicketsByTicket() {
        //given
        PersonalTicket personalTicket=new PersonalTicket(1l,false,null,null,null);
        PersonalTicket personalTicket1=new PersonalTicket(2l,false,null,null,null);
        underTest.save(personalTicket);
        underTest.save(personalTicket1);
        //when

        PersonalTicket lastPersonalTicket=underTest.findLastPersonalTicket();
        boolean expected=personalTicket.equals(lastPersonalTicket);
        //then
        assertThat(expected).isFalse();
    }
}