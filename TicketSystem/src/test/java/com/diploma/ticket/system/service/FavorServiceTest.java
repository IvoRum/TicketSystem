package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
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

import java.sql.Time;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
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
    @Disabled
    void addNewService() {
        String name="Favor name";
        Long id=1l;
        FavorCreationReqest reqest
                =FavorCreationReqest
                .builder()
                .name(name)
                .description("somthing")
                .workStart(new Time(1200l))
                .workEnd(new Time(1900l))
                .build();

        Favor favor
                =Favor
                .builder()
                .id(id)
                .name(reqest.getName())
                .description(reqest.getDescription())
                .workStart(reqest.getWorkStart())
                .workEnd(reqest.getWorkEnd())
                .build();


        given(favorRepository.save(favor))
               .willReturn(favor);


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
    void findFavorFromRepository() throws NotFountInRepositoryException {
        Long id=1l;
        Favor favor
                =Favor.builder().id(id).build();
        Optional<Favor> optionalFavor= Optional.ofNullable(favor);
        given(favorRepository.findById(id))
                .willReturn(optionalFavor);
        Favor foundFavor=
        underTest.findFavorFromRepository(favor.getId());

        assertThat(foundFavor).isEqualTo(favor);
    }

    @Test
    void deleteFavor() {
        //when
        Long id=1l;
        Favor favor=Favor.builder().id(id).build();

        Optional<Favor> favorOptional= Optional.ofNullable(favor);
        given(favorRepository.findById(id)).willReturn(favorOptional);
        underTest.deleteFavor(favor.getId());
        verify(favorRepository).delete(favor);

    }
}