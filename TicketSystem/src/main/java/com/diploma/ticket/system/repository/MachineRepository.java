package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine,Long> {
    @Query("SELECT m FROM Machine m WHERE m.name= ?1")
    Optional<Machine> findMachineByName(String name);
}

