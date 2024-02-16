package com.example.monitoringmicroservice.repositories;

import com.example.monitoringmicroservice.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    public List<Measurement> getAllByDeviceID_Id(Integer deviceID);
}
