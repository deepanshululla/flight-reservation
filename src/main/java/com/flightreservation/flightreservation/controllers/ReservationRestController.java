package com.flightreservation.flightreservation.controllers;

import com.flightreservation.flightreservation.domains.Reservation;
import com.flightreservation.flightreservation.dto.ReservationUpdateRequest;
import com.flightreservation.flightreservation.exceptions.ReservationNotFound;
import com.flightreservation.flightreservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        // handle error here what if no reservation found
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if(!reservation.isPresent()){throw new ReservationNotFound("No reservation exist with id "+id);}
        return reservation.get();
    }


    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest reservationUpdateRequest){
        Optional<Reservation> reservation=reservationRepository.findById(reservationUpdateRequest.getId());
        // handle error here what if no reservation found
        if(!reservation.isPresent()){throw new ReservationNotFound("No reservation exist with id "+reservationUpdateRequest.getId());}
        reservation.get().setNumberOfBags(reservationUpdateRequest.getNumberOfBags());
        reservation.get().setCheckedin(reservationUpdateRequest.isCheckedIn());

        return reservationRepository.save(reservation.get());
    }
}
