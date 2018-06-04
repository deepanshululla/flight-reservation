package com.flightreservation.flightreservation.repositories;

import com.flightreservation.flightreservation.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);

}
