package com.diploma.TicketSystem.Ticketing.personalTicket;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

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
    void findPersonalTicketByNUmberExist() {
        //given
        Long personalTicketnumber=new Long(55);
        PersonalTicket personalTicket=new PersonalTicket(personalTicketnumber,true);
        underTest.save(personalTicket);
        //when
        Optional<PersonalTicket> exist=underTest.findPersonalTicketByNUmber(personalTicketnumber);
        //den
        assertThat(exist.isPresent()).isTrue();
    }

    @Test
    void fundPersonalTicketByNUmberNotExist() {
        //given
        Long personalTicketnumber=new Long(55);
        Long persanalTicketnumber1=new Long(56);
        PersonalTicket personalTicket=new PersonalTicket(personalTicketnumber,true);
        underTest.save(personalTicket);
        //when
        Optional<PersonalTicket> exist=underTest.findPersonalTicketByNUmber(persanalTicketnumber1);
        //den
        assertThat(exist.isPresent()).isFalse();
    }
}