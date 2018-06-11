package com.flightreservation.flightreservation.controllers;

import com.flightreservation.flightreservation.domains.Reservation;
import com.flightreservation.flightreservation.dto.ReservationUpdateRequest;
import com.flightreservation.flightreservation.exceptions.ReservationNotFound;
import com.flightreservation.flightreservation.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

    @RequestMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        // handle error here what if no reservation found
        LOGGER.info("Inside findReservation() for id: " + id);
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if(!reservation.isPresent()){
            LOGGER.error("No reservation exist with id "+id);
            throw new ReservationNotFound("No reservation exist with id "+id);
        }
        return reservation.get();
    }


    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest reservationUpdateRequest){
        Optional<Reservation> reservation=reservationRepository.findById(reservationUpdateRequest.getId());
        // handle error here what if no reservation found
        LOGGER.info("Inside updateReservation() for " + reservationUpdateRequest);
        if(!reservation.isPresent()){
            LOGGER.error("No reservation exist with id "+reservationUpdateRequest.getId());
            throw new ReservationNotFound("No reservation exist with id "+reservationUpdateRequest.getId());
        }
        reservation.get().setNumberOfBags(reservationUpdateRequest.getNumberOfBags());
        reservation.get().setCheckedin(reservationUpdateRequest.isCheckedIn());
        LOGGER.info("Saving Reservation");
        return reservationRepository.save(reservation.get());
    }
}
