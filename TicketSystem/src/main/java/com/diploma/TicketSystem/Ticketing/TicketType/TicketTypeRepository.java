package com.diploma.TicketSystem.Ticketing.TicketType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TicketTypeRepository extends JpaRepository<TicketType,Long>, JpaSpecificationExecutor<TicketType> {
    @Query("SELECT t FROM TicketType t WHERE t.name= ?1")
    Optional<TicketType> findByTicketTypesName(String name);
}
