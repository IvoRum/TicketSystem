package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Counter;
import com.diploma.ticket.system.entity.Favor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class FavorRepositoryTest {
    @Autowired
    private FavorRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindFavorByName() {
        //given
        String name="FavorNameEx";
        Favor favor=new Favor(1l,name,"some description",new Time(100000),new Time(120000),null,null);

        underTest.save(favor);
        //when

        boolean expected=underTest.findFavorByName(name).isPresent();

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfFindFavorByNameDoseNotExists() {
        //given
        String name="FavorNameEx";
        String falseName="false name";
        Favor favor=new Favor(2l,name,"some description",new Time(100000),new Time(120000),null,null);

        underTest.save(favor);
        //when

        boolean expected=underTest.findFavorByName(falseName).isPresent();

        //then
        assertThat(expected).isFalse();
    }
}