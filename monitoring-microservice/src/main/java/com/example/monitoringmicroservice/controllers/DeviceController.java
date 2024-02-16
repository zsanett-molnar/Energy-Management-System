package com.example.monitoringmicroservice.controllers;

import com.example.monitoringmicroservice.entities.Device;
import com.example.monitoringmicroservice.services.DeviceService;
import com.example.monitoringmicroservice.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        deviceService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Device>> getAll(){
        List<Device> devices = deviceService.getAll();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<List<Device>> getByUser(@PathVariable("userid") Integer userID) {
        List<Device> devices = deviceService.getByUser(userID);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

}
