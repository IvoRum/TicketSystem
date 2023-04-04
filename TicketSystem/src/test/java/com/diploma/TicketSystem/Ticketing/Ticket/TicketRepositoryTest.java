package com.diploma.TicketSystem.Ticketing.Ticket;

import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
import com.diploma.TicketSystem.Ticketing.TicketType.TicketTypeBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TicketRepositoryTest {
    @Autowired
    private TicketRepository underTest;
    @Test
    void itShouldCheckIfTicketfindByTicketNumber() {
        //given
        TicketType type=new
                TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();

        listOfTypes.add(type);

        Long ticketNumber = (long)55;
        underTest.saveAllAndFlush(List.of(
                new Ticket("DA",ticketNumber,listOfTypes),
                new Ticket("DA",(long)66,listOfTypes),
                new Ticket("DA", (long)77,listOfTypes)
        ));

        //when
        Optional<Ticket> exists=underTest.findByTicketNumber(ticketNumber);

        //then
        assertThat(exists.isPresent()).isTrue();

    }
}