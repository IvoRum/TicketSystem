package com.diploma.TicketSystem.Ticketing.Ticket;

import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
import com.diploma.TicketSystem.Ticketing.TicketType.TicketTypeBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;

    private TicketService underTest;
    @BeforeEach
    void setUp(){
        underTest=new TicketService(ticketRepository);
    }


    @Test
    void canGetTickets() {
        //when
        underTest.getTickets();
        //then
        verify(ticketRepository).findAll();
    }

    @Test
    void canAddNewTicket() {
        //given
        TicketType type=new
                TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();

        listOfTypes.add(type);

        Long ticketNumber = (long)55;
        Ticket ticket=new Ticket("DA",ticketNumber,listOfTypes);
        //when
        underTest.addNewTicket(ticket);

        //then
        ArgumentCaptor<Ticket> ticketArgumentCaptor=
                ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepository).save(ticketArgumentCaptor.capture());

        Ticket capturedTicket=ticketArgumentCaptor.getValue();
        assertThat(capturedTicket).isEqualTo(ticket);
    }

    @Test
    void WillThrowWhenTicketTaken() {
        //given
        TicketType type=new
                TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();

        listOfTypes.add(type);

        Long ticketNumber = (long)55;
        Ticket ticket=new Ticket("DA",ticketNumber,listOfTypes);
        //when
        given(ticketRepository.findByTicketNumber(ticket.getNumber()))
                .willReturn(Optional.of(ticket));
        //then
        assertThatThrownBy(()->underTest.addNewTicket(ticket))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Number is taken");

        verify(ticketRepository,never()).save(any());
    }

    @Test
    @Disabled
    void canUpdateTicket() {
        //given
        TicketType type=new
                TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        List<TicketType> listOfTypes= new ArrayList<>();

        listOfTypes.add(type);

        Long ticketNumber = (long)55;
        Long ticketUpdateNumber= (long) 56;
        Ticket ticket=new Ticket("DA",ticketNumber,listOfTypes);
        Ticket updatedTicket=new Ticket("NE",ticketUpdateNumber,listOfTypes);
        //when
        underTest.addNewTicket(ticket);
        underTest.updateTicket(ticketNumber,updatedTicket);
        //then
        ArgumentCaptor<Ticket> ticketArgumentCaptor=
                ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepository).save(ticketArgumentCaptor.capture());

        Ticket capturedTicket=ticketArgumentCaptor.getValue();
        assertThat(capturedTicket).isEqualTo(updatedTicket);
    }
}