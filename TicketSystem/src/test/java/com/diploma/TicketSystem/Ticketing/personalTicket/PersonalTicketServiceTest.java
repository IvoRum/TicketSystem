package com.diploma.TicketSystem.Ticketing.personalTicket;

import com.diploma.TicketSystem.entity.PersonalTicket;
import com.diploma.TicketSystem.repository.PersonalTicketRepository;
import com.diploma.TicketSystem.service.PersonalTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonalTicketServiceTest {

    @Mock
    private PersonalTicketRepository personalTicketRepository;

    private PersonalTicketService underTest;
    @BeforeEach
    void setUp() {
        underTest=new PersonalTicketService(personalTicketRepository);
    }

    @Test
    void canGetPersonaTickets() {
        //when
        underTest.getPersonaTickets();
        //then
        verify(personalTicketRepository).findAll();
    }

    @Test
    void canAddNewPersonalTicket() {
        //given
        long valuerForTest=55L;
        Long personalTicketnumber=valuerForTest;
        PersonalTicket personalTicket=new PersonalTicket(personalTicketnumber,true);
        //when
        underTest.addNewPersonalTicket(personalTicket);
        //then
        ArgumentCaptor<PersonalTicket> personaTicketArgumentCaptor=
                ArgumentCaptor.forClass(PersonalTicket.class);
        verify(personalTicketRepository).save(personaTicketArgumentCaptor.capture());

        PersonalTicket capturedPersonalTicket=personaTicketArgumentCaptor.getValue();
        assertThat(capturedPersonalTicket).isEqualTo(personalTicket);
    }
    @Test
    void WilltrowWhenAddNewPersonalTicket() {
        //given
        long valuerForTest=55L;
        Long personalTicketnumber=valuerForTest;
        PersonalTicket personalTicket=new PersonalTicket(personalTicketnumber,true);
        //when
        given(personalTicketRepository.findPersonalTicketByNUmber(personalTicket.getNumber()))
                .willReturn(Optional.of(personalTicket));
        //then
        assertThatThrownBy(()->underTest.addNewPersonalTicket(personalTicket))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Number is taken");

        verify(personalTicketRepository,never()).save(any());
    }
    //TODO Think of way too do this test
    @Test
    @Disabled
    void updatePersonalTicket() {
    }
}