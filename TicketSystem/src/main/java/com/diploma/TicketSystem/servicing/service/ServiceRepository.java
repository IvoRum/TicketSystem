package com.diploma.TicketSystem.servicing.service;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import com.diploma.TicketSystem.conuter.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT s FROM Service s WHERE s.name= ?1")
    Optional<Service> findServiceByName(String name);
}
