package com.energyapp.devicemanagement.repositories;

import com.energyapp.devicemanagement.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
}
