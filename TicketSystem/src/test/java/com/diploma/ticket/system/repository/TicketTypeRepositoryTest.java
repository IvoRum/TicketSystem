package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.TicketType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TicketTypeRepositoryTest {

    @Autowired
    private TicketTypeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindByTicketTypesName() {
        String name="Ticket type name";
        TicketType ticketType=new TicketType();
        ticketType.setName(name);

        underTest.save(ticketType);

        Optional<TicketType> ticketTypeOptional=
            underTest.findByTicketTypesName(name);

        assertThat(ticketTypeOptional.isPresent()).isTrue();
    }

    @Test
    void itShouldNotFindByTicketTypesName() {
        String name="Ticket type name";
        String falseName="False name";
        TicketType ticketType=new TicketType();
        ticketType.setName(name);

        underTest.save(ticketType);

        Optional<TicketType> ticketTypeOptional=
                underTest.findByTicketTypesName(falseName);

        assertThat(ticketTypeOptional.isPresent()).isFalse();
    }
}