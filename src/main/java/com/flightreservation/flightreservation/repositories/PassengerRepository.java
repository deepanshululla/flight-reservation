package com.flightreservation.flightreservation.repositories;

import com.flightreservation.flightreservation.domains.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
