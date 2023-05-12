package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.entity.Counter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Time;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CounterRepositoryTest {

    @Autowired
    private CounterRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void  itShouldCheckIfCounterExistsByName() {
        //given
        String name="CounterNameEx";
        Counter counter= new Counter(1l,name,"Some description",1,false,false,null);

        underTest.save(counter);
        //when

        boolean expected=underTest.findCounterByName(name).isPresent();

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void  itShouldCheckIfCounterDoseNotExistsByName() {
        //given
        String name="CounterNameEx";
        String falseName="False Name";
        Counter counter= new Counter(1l,name,"Some description",1,false,false,null);

        underTest.save(counter);
        //when

        boolean expected=underTest.findCounterByName(falseName).isPresent();

        //then
        assertThat(expected).isFalse();
    }
}