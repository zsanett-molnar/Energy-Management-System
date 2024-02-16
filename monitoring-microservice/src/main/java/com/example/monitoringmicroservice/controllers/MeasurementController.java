package com.example.monitoringmicroservice.controllers;

import com.example.monitoringmicroservice.dtos.MeasurementDTO;
import com.example.monitoringmicroservice.entities.Measurement;
import com.example.monitoringmicroservice.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping()
    public ResponseEntity<Integer> insertProsumer(@RequestBody Measurement measurement) {
        Integer measurementID = measurementService.insert(measurement);
        return new ResponseEntity<>(measurementID, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        measurementService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<List<Measurement>> getAll() {
        List<Measurement> measurements = measurementService.getAll();
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }

    @GetMapping("/deviceid/{deviceid}")
    public ResponseEntity<List<Measurement>> getAll(@PathVariable("deviceid") Integer deviceID) {
        List<Measurement> measurements = measurementService.getByDeviceID(deviceID);
        return new ResponseEntity<>(measurements, HttpStatus.OK);
    }

}
