package com.flightreservation.flightreservation.controllers;

import com.flightreservation.flightreservation.domains.Flight;
import com.flightreservation.flightreservation.domains.Reservation;
import com.flightreservation.flightreservation.dto.ReservationRequest;
import com.flightreservation.flightreservation.exceptions.FlightNotFound;
import com.flightreservation.flightreservation.repositories.FlightRepository;
import com.flightreservation.flightreservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ReservationController {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap){
        Optional<Flight> flight=flightRepository.findById(flightId);
        // handle error here
        if(!flight.isPresent()){throw new FlightNotFound("flightId "+flightId);}
        modelMap.addAttribute("flight",flight);
        return "reservation/completeReservation";
    }

    @RequestMapping(value = "/completeReservation",method = RequestMethod.POST)
    public String completeReservation(ReservationRequest reservationRequest, ModelMap modelMap){
        Reservation reservation=reservationService.bookFlight(reservationRequest);
        modelMap.addAttribute("msg","Reservation created successfully and the reservation id is "+reservation.getId());
        return "reservation/reservationConfirmation";
    }

}
