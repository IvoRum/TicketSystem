package com.diploma.TicketSystem.conuter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CounterRepository extends JpaRepository<Counter,Long> {
    @Query("SELECT c FROM Counter c WHERE c.name= ?1")
    Optional<Counter> findCounterByName(String name);

}
