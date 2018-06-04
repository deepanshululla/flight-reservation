package com.flightreservation.flightreservation.repositories;

import com.flightreservation.flightreservation.domains.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{
}
