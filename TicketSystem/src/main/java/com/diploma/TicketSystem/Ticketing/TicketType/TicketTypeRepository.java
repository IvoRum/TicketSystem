package com.diploma.TicketSystem.Ticketing.TicketType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketType,Long> {

    @Query("SELECT t FROM ticket_type t WHERE t.name= ?1")
    Optional<TicketType> findByTicketName(String name);
}
