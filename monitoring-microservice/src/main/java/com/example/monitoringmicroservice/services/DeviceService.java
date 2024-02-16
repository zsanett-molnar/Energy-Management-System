package com.example.monitoringmicroservice.services;

import com.example.monitoringmicroservice.entities.Device;
import com.example.monitoringmicroservice.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Integer insert(Device device) {
        deviceRepository.save(device);
        return device.getId();
    }


    public void deleteAll() {
        deviceRepository.deleteAll();
    }

    public void deleteByID(Integer id) {
        deviceRepository.deleteById(id);
    }

    public Optional<Device> getByID(Integer id) {
        return deviceRepository.findById(id);
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public List<Device> getByUser(Integer userID) {
        return deviceRepository.findAllByUserID(userID);
    }

}
