package com.example.monitoringmicroservice.repositories;

import com.example.monitoringmicroservice.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    public List<Device> findAllByUserID(Integer userid);
}
