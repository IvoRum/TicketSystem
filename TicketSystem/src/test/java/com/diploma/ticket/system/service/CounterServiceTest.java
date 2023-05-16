package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.repository.CounterRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CounterServiceTest {

    @Mock
    private CounterRepository counterRepository;

    private CounterService underTest;

    @BeforeEach
    void setUp() {
        //underTest=new CounterService(counterRepository, favorRepository);
    }

    @Test
    @Disabled
    void getCounters() {
        //when
        underTest.getCounters();
        //then
        verify(counterRepository).findAll();
    }

    @Test
    @Disabled
    void canAddNewCounter() {
        //given
        String name="Counter name";
        Counter counter = Counter.builder().name(name).build();
        //when
        underTest.addNewCounter(counter);
        //then
        ArgumentCaptor<Counter> argumentCaptor
                =ArgumentCaptor.forClass(Counter.class);
        verify(counterRepository).save(argumentCaptor.capture());

        Counter captorArticle=argumentCaptor.getValue();

        assertThat(captorArticle).isEqualTo(counter);
    }

    @Test
    @Disabled
    void wontAddNewCounter() {
        //given
        String name="Counter name";
        Counter counter = Counter.builder().name(name).build();
        //when then
        Optional<Counter> optional= Optional.of(counter);
        given(counterRepository.findCounterByName(anyString()))
                .willReturn(optional);
        //when
        assertThatThrownBy(()->underTest.addNewCounter(counter))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name is taken");

    }


    @Disabled
    @Test
    void updateCounter() {
    }

    @Test
    @Disabled
    void addNewFavor() {
    }

    @Test
    @Disabled
    void deleteCounter() {
        //when
        Long id=1l;
        String name="Counter name";
        Counter counter = Counter.builder().id(id).name(name).build();

        Optional<Counter> optional= Optional.of(counter);
        given(counterRepository.findById(id))
                .willReturn(optional);
        underTest.deleteCounter(counter.getId());
        //then
        verify(counterRepository).delete(counter);
    }

    @Test
    @Disabled
    void findCounter() {
    }
}