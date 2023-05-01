package com.diploma.ticket.system.repository;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>, JpaSpecificationExecutor<Ticket> {
    @Query("SELECT t FROM Ticket t WHERE t.name= ?1")
    Optional<Ticket> findByTicketName(String name);
    @Query("SELECT f FROM Ticket f WHERE EXISTS (SELECT t FROM f.favor t WHERE t.id = ?1 )")
    List<Ticket> findByTicketFavor(Long favor);
}

