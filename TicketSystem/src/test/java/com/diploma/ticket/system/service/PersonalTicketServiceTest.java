package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.repository.PersonalTicketRepository;
import com.diploma.ticket.system.repository.UserRepository;
import com.diploma.ticket.system.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonalTicketServiceTest {
    @Mock
    private PersonalTicketRepository personalTicketRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtUtil jwtUtil;
    private TicketService ticketService;
    private PersonalTicketService underTest;

    @BeforeEach
    void setUp() {
        underTest=new PersonalTicketService(
                personalTicketRepository,
                userRepository,
                jwtUtil,
                ticketService
        );
    }

    @Test
    @Disabled
    void updatePersonalTicket() {
    }

    @Test
    void getPersonaTickets() {
        underTest.getPersonaTickets();
        verify(personalTicketRepository).findAll();
    }

    @Test
    void addNewPersonalTicket() {
        Long id=1l;
        PersonalTicket personalTicket=PersonalTicket.builder().id(id).build();

        underTest.addNewPersonalTicket(personalTicket);

        ArgumentCaptor<PersonalTicket> argumentCaptor
                =ArgumentCaptor.forClass(PersonalTicket.class);
        verify(personalTicketRepository).save(argumentCaptor.capture());

        PersonalTicket capturedPersonalTicket
                =argumentCaptor.getValue();

        assertThat(capturedPersonalTicket).isEqualTo(personalTicket);
    }

    @Test
    @Disabled
    void finishTicket() {
        Long ticketNumber=1l;

        underTest.finishTicket(ticketNumber,anyString());


    }

    @Test
    void findPersonalTicketByIdOfPersonalTicket() {
    }

    @Test
    void deletePersonalTicket() {
    }

    @Test
    void findActivePersonalTicketByTicket() {
    }

    @Test
    void setTicketToUser() {
    }

    @Test
    void getLastPersonTicketId() {
    }

    @Test
    void findAllAndJoinTicket() {
    }
}