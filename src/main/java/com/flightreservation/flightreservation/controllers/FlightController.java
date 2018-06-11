package com.flightreservation.flightreservation.controllers;

import com.flightreservation.flightreservation.domains.Flight;
import com.flightreservation.flightreservation.repositories.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    private static final Logger LOGGER= LoggerFactory.getLogger(FlightController.class);

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping("/findFlights")
    public String findFLights(@RequestParam("source") String source, @RequestParam("destination") String destination,
                              @RequestParam("departDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departDate, ModelMap modelMap) {

        LOGGER.info("Inside findFlights() From:" + source + " TO:" + destination + "Departure Date: " + departDate);
        List<Flight> flights =flightRepository.findFlights(source,destination,departDate);
        modelMap.addAttribute("flights",flights);
        LOGGER.info("Flights found are"+flights.toString());
        return "flights/displayFlights";
    }

    @RequestMapping("/admin/showAddFlight")
    public String showAddFlightPage(){
        return "flights/addFlight";
    }

    @RequestMapping("/admin/addFlight")
    public String addFlight(@ModelAttribute("flight") Flight flight,ModelMap modelmap){
       flightRepository.save(flight);
       modelmap.addAttribute("msg","Flight Added Successfully");
       return "flights/addFlight";
    }

}
