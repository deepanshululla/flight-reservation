package com.flightreservation.flightreservation.controllers;

import com.flightreservation.flightreservation.domains.User;
import com.flightreservation.flightreservation.exceptions.UserAlreadyRegistered;
import com.flightreservation.flightreservation.exceptions.UserNotFound;
import com.flightreservation.flightreservation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        return "login/registerUser";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        //handle error here what is email already exists.
        Optional<User> foundUser= userRepository.findByEmail(user.getEmail());
        if(foundUser.isPresent()){throw new UserAlreadyRegistered("Email exists: "+user.getEmail());}
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping("/showLogin")
    public String showLoginPage() {
        return "login/login";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap){
        Optional<User> foundUser=userRepository.findByEmail(email);
        // handle error here what if no user found
        if(!foundUser.isPresent()){throw new UserNotFound("Email not found: "+email);}
        if(foundUser.get().getPassword().equals(password)) {
            return "flights/findFlights";
        } else {
            modelmap.addAttribute("msg","Invalid username or password");
        }
        return "login/login";
    }

}
