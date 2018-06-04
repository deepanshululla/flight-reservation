package com.flightreservation.flightreservation.controllers;

import com.flightreservation.flightreservation.domains.Reservation;
import com.flightreservation.flightreservation.dto.ReservationUpdateRequest;
import com.flightreservation.flightreservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        return reservationRepository.findById(id).get();
    }


    @RequestMapping(value = "/reservations", method = RequestMethod.PUT)
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest reservationUpdateRequest){
        Reservation reservation=reservationRepository.findById(reservationUpdateRequest.getId()).get();
        reservation.setNumberOfBags(reservationUpdateRequest.getNumberOfBags());
        reservation.setCheckedin(reservationUpdateRequest.isCheckedIn());

        return reservationRepository.save(reservation);
    }
}
