package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketType,Long>, JpaSpecificationExecutor<TicketType> {
    @Query("SELECT t FROM TicketType t WHERE t.name= ?1")
    Optional<TicketType> findByTicketTypesName(String name);
}
