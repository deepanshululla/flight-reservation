package com.flightreservation.flightreservation.repositories;

import com.flightreservation.flightreservation.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
