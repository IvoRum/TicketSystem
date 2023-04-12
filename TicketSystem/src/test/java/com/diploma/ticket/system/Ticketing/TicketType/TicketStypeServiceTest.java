package com.diploma.ticket.system.Ticketing.TicketType;

import com.diploma.ticket.system.entity.TicketType;
import com.diploma.ticket.system.repository.TicketTypeRepository;
import com.diploma.ticket.system.service.TicketStypeService;
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
class TicketStypeServiceTest {
    @Mock
    private TicketTypeRepository ticketTypeRepository;
    private TicketStypeService underTest;

    @BeforeEach
    void setUp() {
        underTest=new TicketStypeService(ticketTypeRepository);
    }

    @Test
    void getTicketTypes() {
        //when
        underTest.getTicketTypes();
        //then
        verify(ticketTypeRepository).findAll();
    }

    @Test
    void canAddNewTicket() {
        //given
        TicketType type=new
                TicketType.TicketTypeBuilder()
                .setDiscription("Some description")
                .setChekedTicket(true).build();
        //when
        underTest.addNewTicket(type);
        //then
        ArgumentCaptor<TicketType> ticketTypeArgumentCaptor=
                ArgumentCaptor.forClass(TicketType.class);
        verify(ticketTypeRepository).save(ticketTypeArgumentCaptor.capture());

        TicketType captureTicketType=ticketTypeArgumentCaptor.getValue();
        assertThat(captureTicketType).isEqualTo(type);
    }
    @Test
    void WillThrowWhenTicketTaken() {
        //given
        TicketType type=new
                TicketType.TicketTypeBuilder().setDiscription("Some description").setChekedTicket(true).build();
        //when
        given(ticketTypeRepository.findByTicketTypesName(type.getName()))
                .willReturn(Optional.of(type));
        //then
        assertThatThrownBy(()->underTest.addNewTicket(type))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Name is taken");

        verify(ticketTypeRepository,never()).save(any());
    }


    @Test
    @Disabled
    void updateTicket() {
    }
}