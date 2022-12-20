package com.udemy.flightReservation.repository;

import com.udemy.flightReservation.entity.Flight;
import com.udemy.flightReservation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {

    @Query(value = "from Passenger where firstName=:firstName and lastName=:lastName and birthdate=:birthdate ORDER BY id ASC")
    List<Passenger> findPassenger(@Param("firstName")String firstName, @Param("lastName") String lastName, @Param("birthdate") Date birthdate);

}
