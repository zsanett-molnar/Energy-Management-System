package com.energyapp.devicemanagement.controllers;


import com.energyapp.devicemanagement.dtos.DeviceDetailsDTO;
import com.energyapp.devicemanagement.dtos.UpdateDeviceDTO;
import com.energyapp.devicemanagement.entities.Device;
import com.energyapp.devicemanagement.services.DeviceService;
import com.energyapp.devicemanagement.services.JwtService;
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

    private final JwtService jwtService;

    @Autowired
    public DeviceController(DeviceService deviceService, JwtService jwtService) {
        this.deviceService = deviceService;
        this.jwtService = jwtService;
    }

    @GetMapping()
    public ResponseEntity<List<DeviceDetailsDTO>> getDevices(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        List<DeviceDetailsDTO> dtos = deviceService.findDevices();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeviceDetailsDTO> getDevice(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable("id") Integer deviceId) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        DeviceDetailsDTO dto = deviceService.findDeviceById(deviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/freedevices")
    public ResponseEntity<List<Device>> getFreeDevices() {
        List<Device> freeDevices = deviceService.findFreeDevices();
        return new ResponseEntity<>(freeDevices, HttpStatus.OK);
    }


    @GetMapping(value = "/getByUser/{id}")
    public ResponseEntity<List<Device>> getDevicesByUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable("id") Integer userId) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        List<Device> devices = deviceService.findDeviceByUser(userId);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> insertProsumer(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @RequestBody DeviceDetailsDTO deviceDTO) throws Exception {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        Integer deviceID = deviceService.insert(deviceDTO);
        return new ResponseEntity<>(deviceID, HttpStatus.OK);
    }

    @PutMapping(value = "/updateDevices/{id}")
    public ResponseEntity<Integer> updateDevicesByDeletedUser(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable("id") Integer userId) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the header is not present or does not start with "Bearer "
        }

        String token = authorizationHeader.substring(7);
        System.out.println(token);

        if (!jwtService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized if the token is invalid
        }

        List<Device> devices = deviceService.findDeviceByUser(userId);
        for(Device d : devices) {
            d.setUserID(null);
        }
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<String> deleteDevice(@PathVariable("id") Integer deviceID) {
//        boolean isDeleted = deviceService.deleteDeviceByID(deviceID);
//        if(isDeleted==true) {
//            return new ResponseEntity<>("Device deleted successfully!", HttpStatus.OK);
//        }
//        else {
//            return  new ResponseEntity<>("Device not found!", HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Device> deleteDevice(@PathVariable("id") Integer deviceID) {
        Device deletedDevice = deviceService.deleteDeviceByID2(deviceID);
        if(deletedDevice!=null) {
            return new ResponseEntity<>(deletedDevice, HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>(deletedDevice, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteAll")
    public void deleteAllDevices() {
        deviceService.deleteAllDevices();
    }


    //asociere user la device
    @PutMapping(value = "/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable("id") Integer deviceID,@RequestBody UpdateDeviceDTO dto) throws Exception {
        Device device = deviceService.updateDeviceById(dto, deviceID);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
}
