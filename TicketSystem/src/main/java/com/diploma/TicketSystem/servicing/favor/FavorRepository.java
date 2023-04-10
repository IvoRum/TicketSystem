package com.diploma.TicketSystem.servicing.favor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FavorRepository extends JpaRepository<Favor, Long> {
    //Ramember the name of the Query needs
    // to be the name of the class not the table
    @Query("SELECT s FROM Favor s WHERE s.name= ?1")
    Optional<Favor> findServiceByName(String name);
}
