package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.PersonalTicket;
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
