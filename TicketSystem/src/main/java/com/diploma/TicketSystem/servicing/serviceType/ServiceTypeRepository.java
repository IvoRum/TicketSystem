package com.diploma.TicketSystem.servicing.serviceType;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType,Long> {

    @Query("SELECT s FROM ServiceType s WHERE s.name= ?1")
    Optional<ServiceType> findServiceTypeByName(String name);
}
