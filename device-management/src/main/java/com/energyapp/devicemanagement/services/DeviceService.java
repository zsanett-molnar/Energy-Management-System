package com.energyapp.devicemanagement.services;

import com.energyapp.devicemanagement.controllers.exceptions.ResourceNotFoundException;
import com.energyapp.devicemanagement.dtos.DeviceDetailsDTO;
import com.energyapp.devicemanagement.dtos.UpdateDeviceDTO;
import com.energyapp.devicemanagement.dtos.builders.DeviceBuilder;
import com.energyapp.devicemanagement.entities.AppUser;
import com.energyapp.devicemanagement.entities.Device;
import com.energyapp.devicemanagement.message_producer.DeviceMessageProducer;
import com.energyapp.devicemanagement.repositories.DeviceRepository;
import com.energyapp.devicemanagement.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    private final UserRepository userRepository;

    private DeviceMessageProducer messageProducer;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.messageProducer = new DeviceMessageProducer();
    }

    public List<DeviceDetailsDTO> findDevices() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDetailsDTO)
                .collect(Collectors.toList());
    }

    public DeviceDetailsDTO findDeviceById(Integer id) {
        Optional<Device> prosumerOptional = deviceRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Device with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toDeviceDetailsDTO(prosumerOptional.get());
    }

    public List<Device> findFreeDevices() {
        //List<Device> allDevices = deviceRepository.findAll();
        List<Device> freeDevices = deviceRepository.findByUserIDIsNull();

//        for(Device d : allDevices) {
//            if(d.getUserID()==null) {
//                freeDevices.add(d);
//            }
//        }
        return freeDevices;
    }


    public List<Device> findDeviceByUser(Integer userID) {
        List<Device> usersDevices = deviceRepository.findByUserID_UserID(userID);

        return usersDevices;
    }
    public Integer insert(DeviceDetailsDTO deviceDTO) throws Exception {

        if(deviceDTO.getUserID()!=null) {
            Optional<AppUser> user = userRepository.findById(deviceDTO.getUserID());
            Device device = DeviceBuilder.toEntity(deviceDTO, user.get());
            device = deviceRepository.save(device);

            System.out.println("user id is: " + device.getUserID().getUserID());
            LOGGER.debug("Device with id {} was inserted in db", device.getId());
            messageProducer.SendMessage(device.getId(), device.getMaxConsumption(), device.getUserID().getUserID(), "insert");
            return device.getId();
        }
        else {
            Device device = DeviceBuilder.toEntity(deviceDTO, null);
            device = deviceRepository.save(device);
            LOGGER.debug("Device with id {} was inserted in db", device.getId());
            messageProducer.SendMessage(device.getId(), device.getMaxConsumption(), null, "insert");
            return device.getId();
        }

    }

    public boolean deleteDeviceByID(Integer id) {
        boolean isDeleted = false;
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            deviceRepository.deleteById(id);
            isDeleted = true;
        }
        //System.out.println(isDeleted);
        return isDeleted;
    }

    public Device deleteDeviceByID2(Integer id) {
        boolean isDeleted = false;
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            deviceRepository.deleteById(id);
            isDeleted = true;
        }
        //System.out.println(isDeleted);
        return device.get();
    }

    public void deleteAllDevices() {
        deviceRepository.deleteAll();
    }

    public Device updateDeviceById(UpdateDeviceDTO dto, Integer id) throws Exception {
        Optional<Device> oldDevice = deviceRepository.findById(id);
        oldDevice.get().setDescription(dto.getDescription());
        oldDevice.get().setAddress(dto.getAddress());
        oldDevice.get().setMaxConsumption(dto.getMaxConsumption());

        Optional<AppUser> user;
        if(dto.getUserID()!=null) {
            user = userRepository.findById(dto.getUserID());
            oldDevice.get().setUserID(user.get());
        }

        return deviceRepository.save(oldDevice.get());

    }


    public void deleteByID(Integer id) {
        deviceRepository.deleteById(id);
    }
}
