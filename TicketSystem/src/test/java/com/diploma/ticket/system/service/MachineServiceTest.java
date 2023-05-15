package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.Machine;
import com.diploma.ticket.system.payload.request.MachineCreationRequest;
import com.diploma.ticket.system.payload.response.CreationResponse;
import com.diploma.ticket.system.repository.FavorRepository;
import com.diploma.ticket.system.repository.MachineRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MachineServiceTest {

    @Mock
    private MachineRepository machineRepository;
    @Mock
    private FavorRepository favorRepository;
    private MachineService underTest;

    @BeforeEach
    void setUp() {
        underTest =new MachineService(machineRepository,favorRepository);
    }


    @Test
    void getMachines() {
        underTest.getMachines();
        verify(machineRepository).findAll();
    }

    @Test
    void addNewMachine() {
        //given
        Long id=1l;
        Favor favor=Favor.builder().id(id).build();
        List<Favor> favorList=new ArrayList<>();
        favorList.add(favor);
        String name="Machine name";
        Machine machine=Machine.builder().name(name).favor(favorList).build();

        MachineCreationRequest request=MachineCreationRequest
                .builder().name(name).favorId(id).build();
        //when
        given(favorRepository.findById(id))
                .willReturn(Optional.of(favor));

        underTest.addNewMachine(request);
        //then
        ArgumentCaptor<Machine> argumentCaptor
                =ArgumentCaptor.forClass(Machine.class);
        verify(machineRepository).save(argumentCaptor.capture());

        Machine capturedMachine=argumentCaptor.getValue();

        assertThat(capturedMachine).isEqualTo(machine);
    }

    @Test
    @Disabled
    void updateMachine() {
    }

    @Test
    void deleteMachine() {
        Long id=1l;
        Machine machine=Machine.builder().id(id).build();

        Optional<Machine> optional=Optional.of(machine);
       // when(machineRepository.findById(anyLong()).orElseThrow())
         //       .thenReturn(machine);
        given(machineRepository.findById(id))
                .willReturn(optional);
        underTest.deleteMachine(machine.getId());

        verify(machineRepository).delete(machine);
    }
}