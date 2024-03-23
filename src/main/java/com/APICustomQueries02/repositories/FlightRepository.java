package com.APICustomQueries02.repositories;

import com.APICustomQueries02.entities.Flight;
import com.APICustomQueries02.entities.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f ORDER BY f.fromAirport ASC")
    Page<Flight> findAllOrderedByFromAirportAsc(Pageable pageable);

    //non necessaria perchè la repo già capisce che query fare
    //@Query("SELECT f FROM Flight f WHERE f.status = ?1")
    List<Flight> findByStatus(StatusEnum statusEnum);
    @Query("SELECT f FROM Flight f WHERE f.status = ?1 OR f.status = ?2")
    List<Flight> findByTwoStatus(StatusEnum statusEnum1,StatusEnum statusEnum2);
}
