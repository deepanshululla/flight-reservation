package com.flightreservation.flightreservation.services;

import com.flightreservation.flightreservation.domains.Reservation;
import com.flightreservation.flightreservation.dto.ReservationRequest;


public interface ReservationService {
    public Reservation bookFlight(ReservationRequest reservationRequest);
}
