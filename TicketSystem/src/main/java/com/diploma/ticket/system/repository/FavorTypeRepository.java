package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.FavorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavorTypeRepository extends JpaRepository<FavorType,Long> {

    @Query("SELECT s FROM FavorType s WHERE s.name= ?1")
    Optional<FavorType> findServiceTypeByName(String name);
}