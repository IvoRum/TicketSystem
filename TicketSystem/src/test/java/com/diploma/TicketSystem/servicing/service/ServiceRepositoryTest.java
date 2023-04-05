package com.diploma.TicketSystem.servicing.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Time;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findServiceByNameSouldExist() {
        //given
        Service service=new Service("ServiceName1","ServiceDescription1",new Time(8,0,0),new Time(17,0,0));
        underTest.save(service);
        //when
        Optional<Service> exists=underTest.findServiceByName("ServiceName1");
        //den
        assertThat(exists.isPresent()).isTrue();
    }

    @Test
    void findServiceByNameSouldThrow() {
        //given
        Service service=new Service("ServiceName1","ServiceDescription1",new Time(8,0,0),new Time(17,0,0));
        underTest.save(service);
        //when
        Optional<Service> exists=underTest.findServiceByName("ServiceName2");
        //den
        assertThat(exists.isPresent()).isFalse();
    }
}