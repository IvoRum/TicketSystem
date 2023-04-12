package com.diploma.ticket.system.Ticketing.TicketType;

import com.diploma.ticket.system.entity.TicketType;
import com.diploma.ticket.system.repository.TicketTypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TicketTypeRepositoryTest {
    @Autowired
    private TicketTypeRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByTicketNameShouldExist() {
        //given
        TicketType type=new TicketType.TicketTypeBuilder().setChekedTicket(true).setDiscription("Some Description").setName("TestName1").build();
        underTest.save(type);
        //when
        Optional<TicketType> exists=underTest.findByTicketTypesName("TestName1");
        //den
        assertThat(exists.isPresent()).isTrue();
    }

    @Test
    void findByTicketNameShodeNotExist() {
        //given
        TicketType type=new TicketType.TicketTypeBuilder().setChekedTicket(true).setDiscription("Some Description").setName("TestName1").build();
        underTest.save(type);
        //when
        Optional<TicketType> exists=underTest.findByTicketTypesName("TestName2");
        //den
        assertThat(exists.isPresent()).isFalse();
    }
}