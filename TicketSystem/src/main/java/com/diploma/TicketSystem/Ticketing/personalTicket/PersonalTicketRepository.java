package com.diploma.TicketSystem.Ticketing.personalTicket;

import com.diploma.TicketSystem.Ticketing.Ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalTicketRepository extends JpaRepository<PersonalTicket,Long>, JpaSpecificationExecutor<PersonalTicket> {
    @Query("SELECT t FROM PersonalTicket t WHERE t.number= ?1")
    Optional<PersonalTicket> findPersonalTicketByNUmber(Long number);
}
