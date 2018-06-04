package com.flightreservation.flightreservation.controllers;

import com.flightreservation.flightreservation.domains.User;
import com.flightreservation.flightreservation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping("/showLogin")
    public String showLoginPage() {
        return "login/login";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap){
        User user=userRepository.findByEmail(email);
        System.out.println(user);
        if(user.getPassword().equals(password)) {
            return "flights/findFlights";
        } else {
            modelmap.addAttribute("msg","Invalid username or password");
        }
        return "login/login";
    }

}
