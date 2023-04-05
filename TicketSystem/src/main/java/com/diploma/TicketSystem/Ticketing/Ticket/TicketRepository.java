package com.diploma.TicketSystem.Ticketing.Ticket;
import com.diploma.TicketSystem.Ticketing.personalTicket.PersonalTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>, JpaSpecificationExecutor<Ticket> {
    @Query("SELECT t FROM Ticket t WHERE t.name= ?1")
    Optional<Ticket> findByTicketName(String name);
}

