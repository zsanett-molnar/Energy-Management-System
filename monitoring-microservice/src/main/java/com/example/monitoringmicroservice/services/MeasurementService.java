package com.example.monitoringmicroservice.services;

import com.example.monitoringmicroservice.entities.Device;
import com.example.monitoringmicroservice.entities.Measurement;
import com.example.monitoringmicroservice.repositories.DeviceRepository;
import com.example.monitoringmicroservice.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }


    public Integer insert(Measurement measurement) {
        Measurement newMeasurement = measurementRepository.save(measurement);
        return newMeasurement.getId();
    }

    public void deleteAll() {
        measurementRepository.deleteAll();
    }

    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public List<Measurement> getByDeviceID(Integer deviceID) {
        return measurementRepository.getAllByDeviceID_Id(deviceID);
    }

}
