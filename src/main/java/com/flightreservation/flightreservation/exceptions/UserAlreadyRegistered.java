package com.flightreservation.flightreservation.exceptions;

public class UserAlreadyRegistered extends RuntimeException{
    public UserAlreadyRegistered(String message) {
        super(message);
    }
}
