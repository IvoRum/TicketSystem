package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CounterRepository extends JpaRepository<Counter,Long> {
    @Query("SELECT c FROM Counter c WHERE c.name= ?1")
    Optional<Counter> findCounterByName(String name);

}
