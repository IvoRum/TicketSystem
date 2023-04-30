package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalTicketRepository extends JpaRepository<PersonalTicket,Long>, JpaSpecificationExecutor<PersonalTicket> {
    @Query("SELECT p FROM PersonalTicket p WHERE p.number= ?1")
    Optional<PersonalTicket> findPersonalTicketByNUmber(Long number);
    @Query("SELECT p FROM PersonalTicket p WHERE p.ticket.id = ?1")
    List<PersonalTicket> findPersonalTicketsByTicket(Long ticketID);
}
