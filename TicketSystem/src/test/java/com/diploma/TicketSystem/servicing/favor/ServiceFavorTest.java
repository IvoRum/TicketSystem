package com.diploma.TicketSystem.servicing.favor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ServiceFavorTest {

    @Mock
    private FavorRepository favorRepository;
    private FavorService underTest;

    @BeforeEach
    void setUp(){
        underTest=new FavorService(favorRepository);
    }

    @Test
    void getService() {
        //when
        underTest.getService();
        //den
        verify(favorRepository).findAll();
    }

    @Test
    void addNewService() {
        //given
        Favor favor =new Favor("ServiceName1","ServiceDescription1",new Time(8,0,0),new Time(17,0,0));
        //when
        underTest.addNewService(favor);

        //then
        ArgumentCaptor<Favor> serviceArgumentCaptor=
                ArgumentCaptor.forClass(Favor.class);
        verify(favorRepository).save(serviceArgumentCaptor.capture());

        Favor capturedFavor =serviceArgumentCaptor.getValue();
        assertThat(capturedFavor).isEqualTo(favor);
    }

    //TODO fidure out how to do a update test
    @Test
    @Disabled
    void updateService() {
    }
}