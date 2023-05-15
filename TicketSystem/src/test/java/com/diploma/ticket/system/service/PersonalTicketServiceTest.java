package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        String authHeader="Bearer 123";
        String userEmail="Ivo@mail.com";
        PersonalTicket personalTicket
                =PersonalTicket.builder()
                .id(ticketNumber)
                .ticket(Ticket.builder().build())
                .build();
        User user=
                User.builder()
                        .email(userEmail)
                        .counters(List.of(new Counter()))
                        .build();
        when(userRepository.findByEmail(userEmail))
                .thenReturn(Optional.ofNullable(user));
        given(personalTicketRepository.findById(ticketNumber))
                .willReturn(Optional.ofNullable(personalTicket));


        //then
        underTest.finishTicket(ticketNumber,authHeader);

        ArgumentCaptor<PersonalTicket> argumentCaptor
                =ArgumentCaptor.forClass(PersonalTicket.class);
        verify(personalTicketRepository).save(argumentCaptor.capture());

        PersonalTicket capturedPersonalTicket
                =argumentCaptor.getValue();

        assertThat(capturedPersonalTicket).isEqualTo(personalTicket);
    }

    @Test
    void findPersonalTicketByIdOfPersonalTicket() throws NotFountInRepositoryException {
        Long id=1l;
        PersonalTicket personalTicket
                =PersonalTicket.builder().id(id).build();

        given(personalTicketRepository.findById(id))
                .willReturn(Optional.ofNullable(personalTicket));

        PersonalTicket capturedPersonalTicket
        =underTest.findPersonalTicketByIdOfPersonalTicket(id);

        assertThat(capturedPersonalTicket).isEqualTo(personalTicket);
    }

    @Test
    void deletePersonalTicket() throws NotFountInRepositoryException {
        Long id=1l;
        PersonalTicket personalTicket
                =PersonalTicket.builder().id(id).build();

        given(personalTicketRepository.findById(id))
                .willReturn(Optional.ofNullable(personalTicket));
        underTest.deletePersonalTicket(id);
        verify(personalTicketRepository).delete(personalTicket);
    }

    @Test
    void findActivePersonalTicketByTicket() {
        Long id=1l;
        PersonalTicket personalTicket
                =PersonalTicket.builder().active(true).id(id).build();

        given(personalTicketRepository.findPersonalTicketsByTicket(id))
                .willReturn(List.of(personalTicket));
        List<PersonalTicket> personalTicketList=
        underTest.findActivePersonalTicketByTicket(id);

        assertThat(personalTicketList).isEqualTo(List.of(personalTicket));
    }

    @Test
    @Disabled
    void setTicketToUser() {
    }

    @Test
    void getLastPersonTicketId() {
        Long id=1l;
        PersonalTicket personalTicket
                =PersonalTicket.builder().id(id).build();
        given(personalTicketRepository.findLastPersonalTicket())
                .willReturn(personalTicket);
        underTest.getLastPersonTicketId();
        verify(personalTicketRepository).findLastPersonalTicket();
    }

    @Test
    @Disabled
    void findAllAndJoinTicket() {
    }
}