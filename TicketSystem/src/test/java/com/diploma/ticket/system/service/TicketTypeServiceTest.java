package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.entity.TicketType;
import com.diploma.ticket.system.repository.TicketTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TicketTypeServiceTest {

    @Mock
    private TicketTypeRepository ticketTypeRepository;

    private TicketTypeService underTest;

    @BeforeEach
    void setUp() {
        underTest=new TicketTypeService(ticketTypeRepository);
    }

    @Test
    void getTicketTypes() {
        underTest.getTicketTypes();
        verify(ticketTypeRepository).findAll();
    }

    @Test
    @Deprecated
    void updateTicket() {
    }

    @Test
    void addNewTicket() {
        Long id=1l;
        TicketType ticketType=new TicketType();
        ticketType.setId(id);

        TicketType response=underTest.addNewTicket(ticketType);
        verify(ticketTypeRepository).save(ticketType);

        assertThat(response).isEqualTo(ticketType);
    }

    @Test
    void deleteTicketType() {
        Long id=1l;
        String name="Ticke type name";
        TicketType ticketType=new TicketType();
        ticketType.setId(id);
        ticketType.setName(name);

        given(ticketTypeRepository.findById(id))
                .willReturn(Optional.of(ticketType));

        underTest.deleteTicketType(id);

        verify(ticketTypeRepository).delete(ticketType);
    }
}