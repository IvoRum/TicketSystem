package com.diploma.TicketSystem.servicing.favor;

import com.diploma.TicketSystem.entity.Favor;
import com.diploma.TicketSystem.repository.FavorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Time;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FavorRepositoryTest {

    @Autowired
    private FavorRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findServiceByNameSouldExist() {
        //given
        Favor favor =new Favor("ServiceName1","ServiceDescription1",new Time(8,0,0),new Time(17,0,0));
        underTest.save(favor);
        //when
        Optional<Favor> exists=underTest.findServiceByName("ServiceName1");
        //den
        assertThat(exists.isPresent()).isTrue();
    }

    @Test
    void findServiceByNameSouldThrow() {
        //given
        Favor favor =new Favor("ServiceName1","ServiceDescription1",new Time(8,0,0),new Time(17,0,0));
        underTest.save(favor);
        //when
        Optional<Favor> exists=underTest.findServiceByName("ServiceName2");
        //den
        assertThat(exists.isPresent()).isFalse();
    }
}