package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.*;
import com.diploma.ticket.system.payload.request.TicketCreationRequest;
import com.diploma.ticket.system.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private FavorRepository favorRepository;
    @Mock
    private TicketTypeRepository ticketTypeRepository;
    @Mock
    private PersonalTicketRepository personalTicketRepository;
    @Mock
    private UserRepository userRepository;

    private TicketService underTest;

    @BeforeEach
    void setUp() {
        underTest=new TicketService(
                ticketRepository,
                favorRepository,
                ticketTypeRepository,
                personalTicketRepository,
                userRepository
        );
    }

    @Test
    void getTickets() {
        underTest.getTickets();
        verify(ticketRepository).findAll();
    }

    @Test
    void addNewTicket() {
        Long id=1l;
        String name="Ticket name";
        TicketType type=new TicketType();
        type.setId(id);
        Set<TicketType> ticketTypeSet=new HashSet<>();
        ticketTypeSet.add(type);
        TicketCreationRequest request=
                TicketCreationRequest.builder()
                        .id(id)
                        .name(name)
                        .typeId(id)
                        .build();
        Ticket ticket=
                Ticket.builder()
                        .name(name)
                        .type(ticketTypeSet)
                        .build();

        given(ticketTypeRepository.findById(request.getTypeId()))
                .willReturn(Optional.of(type));
        underTest.addNewTicket(request);
        ArgumentCaptor<Ticket> argumentCaptor
                =ArgumentCaptor.forClass(Ticket.class);
        verify(ticketRepository).save(argumentCaptor.capture());

        Ticket captorValue=argumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(ticket);
    }

    @Test
    @Disabled
    void updateTicket() {
    }

    @Test
    void deleteTicket() {
        Long id=1l;
        Ticket ticket=
                Ticket.builder()
                        .id(id)
                        .build();

        given(ticketRepository.findById(id))
                .willReturn(Optional.ofNullable(ticket));
        underTest.deleteTicket(id);

        verify(ticketRepository).delete(ticket);
    }

    @Test
    void findTicketByFavor() {
        Long id=1l;
        Ticket ticket= Ticket.builder().id(id).build();
        List<Ticket> ticketList=new ArrayList<>();
        ticketList.add(ticket);

        given(ticketRepository.findByTicketFavor(id))
                .willReturn(ticketList);

        List<Ticket> response=underTest.findTicketByFavor(id);
        verify(ticketRepository).findByTicketFavor(id);

        assertThat(response).isEqualTo(ticketList);
    }

    @Test
    void addFavor() {
        Long id=1l;
        Favor favor=Favor.builder().ticket(new HashSet<>()).build();
        Ticket ticket=new Ticket();

        given(favorRepository.findById(anyLong()))
                .willReturn(Optional.of(favor));
        given(ticketRepository.findById(anyLong()))
                .willReturn(Optional.of(ticket));
        underTest.addFavor(id,id);

        ArgumentCaptor<Favor> argumentCaptor
                =ArgumentCaptor.forClass(Favor.class);
        verify(favorRepository).save(argumentCaptor.capture());

        Favor captorValue=argumentCaptor.getValue();

        favor.addTicket(ticket);

        assertThat(captorValue).isEqualTo(favor);
    }

    @Test
    void findById() {
        Long id=1l;
        Ticket ticket= Ticket.builder().build();

        given(ticketRepository.findById(id))
                .willReturn(Optional.ofNullable(ticket));

        Ticket response=underTest.findById(id);

        assertThat(response).isEqualTo(ticket);
    }

    @Test
    void addPersonalTicket() {
        Long id=1l;
        PersonalTicket personalTicket= PersonalTicket.builder().build();
        Ticket ticket= Ticket.builder().personalTickets(new HashSet<>()).build();

        given(personalTicketRepository.findById(id))
                .willReturn(Optional.ofNullable(personalTicket));
        given(ticketRepository.findById(id))
                .willReturn(Optional.ofNullable(ticket));


        underTest.addPersonalTicket(id,id);
        verify(ticketRepository).save(ticket);
    }
}