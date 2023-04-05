package com.diploma.TicketSystem.servicing.service;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;
    private ServiceService underTest;

    @BeforeEach
    void setUp(){
        underTest=new ServiceService(serviceRepository);
    }

    @Test
    void getService() {
        //when
        underTest.getService();
        //den
        verify(serviceRepository).findAll();
    }

    @Test
    void addNewService() {
        //given
        Service service=new Service("ServiceName1","ServiceDescription1",new Time(8,0,0),new Time(17,0,0));
        //when
        underTest.addNewService(service);

        //then
        ArgumentCaptor<Service> serviceArgumentCaptor=
                ArgumentCaptor.forClass(Service.class);
        verify(serviceRepository).save(serviceArgumentCaptor.capture());

        Service capturedService=serviceArgumentCaptor.getValue();
        assertThat(capturedService).isEqualTo(service);
    }

    //TODO fidure out how to do a update test
    @Test
    @Disabled
    void updateService() {
    }
}