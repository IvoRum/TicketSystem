package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.payload.request.FavorCreationReqest;
import com.diploma.ticket.system.repository.FavorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FavorServiceTest {

    @Mock
    private FavorRepository favorRepository;

    private FavorService underTest;


    @BeforeEach
    void setUp() {
        underTest=new FavorService(favorRepository);
    }


    @Test
    void getService() {
        //when
        underTest.getService();
        verify(favorRepository).findAll();
    }

    @Test
    void addNewService() {
        String name="Favor name";
        Favor favor=Favor.builder().name(name).build();
        FavorCreationReqest reqest
                =FavorCreationReqest
                .builder()
                .name(name)
                .build();


        underTest.addNewService(reqest);

        ArgumentCaptor<Favor> argumentCaptor
                =ArgumentCaptor.forClass(Favor.class);

        verify(favorRepository)
                .save(argumentCaptor.capture());

        Favor captorValue=argumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(favor);


    }

    @Disabled
    @Test
    void updateService() {
    }

    @Test
    void findFavorFromRepository() {
    }

    @Test
    void deleteFavor() {
    }
}