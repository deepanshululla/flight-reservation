package com.flightreservation.flightreservation.controllers;

import com.flightreservation.flightreservation.domains.User;
import com.flightreservation.flightreservation.exceptions.UserAlreadyRegistered;
import com.flightreservation.flightreservation.exceptions.UserNotFound;
import com.flightreservation.flightreservation.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/showReg")
    public String showRegistrationPage() {
        LOGGER.info("Inside showRegistrationPage()");
        return "login/registerUser";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        //handle error here what is email already exists.
        LOGGER.info("{} Inside register()", user.getEmail());
        Optional<User> foundUser= userRepository.findByEmail(user.getEmail());
        if(foundUser.isPresent()){
            LOGGER.error("User is already registered with email {} ",user.getEmail());
            throw new UserAlreadyRegistered("Email exists: "+user.getEmail());
        }
        LOGGER.info("Email Exists: "+user.getEmail());
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping("/showLogin")
    public String showLoginPage() {
        LOGGER.info("Inside showLoginPage()");
        return "login/login";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap){
        LOGGER.info("{} Inside login()",email);
        Optional<User> foundUser=userRepository.findByEmail(email);
        // handle error here what if no user found
        if(!foundUser.isPresent()){
            LOGGER.error("Email not found: "+email);
            throw new UserNotFound("Email not found: "+email);
        }
        LOGGER.info("Email Exists: "+email);
        if(foundUser.get().getPassword().equals(password)) {
            return "flights/findFlights";
        } else {
            LOGGER.info("User entered Invalid credentials email:{} and password:{}",email,password);
            modelmap.addAttribute("msg","Invalid username or password");
        }
        return "login/login";
    }

}
