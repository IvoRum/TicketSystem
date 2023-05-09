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
    @Query("SELECT p FROM PersonalTicket p WHERE p.id= ?1")
    Optional<PersonalTicket> findPersonalTicketByNUmber(Long number);

    @Query("SELECT f FROM PersonalTicket f WHERE EXISTS (SELECT t FROM f.ticket t WHERE t.id = ?1  ) AND f.active = true ")
    List<PersonalTicket> findPersonalTicketsByTicket(Long ticketID);

    @Query("SELECT f FROM PersonalTicket f ORDER BY id DESC LIMIT 1")
    PersonalTicket findLastPersonalTicket();
}
