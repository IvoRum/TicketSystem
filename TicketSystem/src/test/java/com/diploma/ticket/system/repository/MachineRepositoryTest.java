package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.Machine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MachineRepositoryTest {
    @Autowired
    private MachineRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindMachineByName() {
        //given
        String name="MachineNameEx";
        Machine machine=new Machine(1l,name,null,null,null);

        underTest.save(machine);
        //when

        boolean expected=underTest.findMachineByName(name).isPresent();

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldNotFindMachineByName() {
        //given
        String name="MachineNameEx";
        String falseName="False name";
        Machine machine=new Machine(1l,name,null,null,null);

        underTest.save(machine);
        //when

        boolean expected=underTest.findMachineByName(falseName).isPresent();

        //then
        assertThat(expected).isFalse();
    }
}