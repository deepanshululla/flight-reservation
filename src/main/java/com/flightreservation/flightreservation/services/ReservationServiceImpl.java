package com.flightreservation.flightreservation.services;

import com.flightreservation.flightreservation.domains.Flight;
import com.flightreservation.flightreservation.domains.Passenger;
import com.flightreservation.flightreservation.domains.Reservation;
import com.flightreservation.flightreservation.dto.ReservationRequest;
import com.flightreservation.flightreservation.repositories.FlightRepository;
import com.flightreservation.flightreservation.repositories.PassengerRepository;
import com.flightreservation.flightreservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation bookFlight(ReservationRequest reservationRequest) {
        // make payment here
        // if the payment is successful proceed..

        Long flightId=reservationRequest.getFlightId();
        Flight flight=flightRepository.findById(flightId).get();

        Passenger passenger=new Passenger();
        passenger.setFirstName(reservationRequest.getPassengerFirstName());
        passenger.setMiddleName(reservationRequest.getPassengerMiddleName());
        passenger.setLastName(reservationRequest.getPassengerLastName());
        passenger.setEmail(reservationRequest.getPassengerEmail());
        passenger.setPhone(reservationRequest.getPassengerPhone());
        passengerRepository.save(passenger);

        Reservation reservation=new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedin(false);
        final Reservation savedReservation = reservationRepository.save(reservation);


        return savedReservation;

    }
}
