package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface FavorRepository extends JpaRepository<Favor, Long> {
    //Ramember the name of the Query needs
    // to be the name of the class not the table
    @Query("SELECT f FROM Favor f WHERE f.name= ?1")
    Optional<Favor> findFavorByName(String name);

}
