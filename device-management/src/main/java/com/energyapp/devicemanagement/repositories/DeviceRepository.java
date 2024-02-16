package com.energyapp.devicemanagement.repositories;

import com.energyapp.devicemanagement.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    List<Device> findByUserID_UserID(Integer userID);

    List<Device> findByUserIDIsNull();
}
