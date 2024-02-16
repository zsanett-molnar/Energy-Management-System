package com.energyapp.devicemanagement.dtos.builders;

import com.energyapp.devicemanagement.dtos.DeviceDTO;
import com.energyapp.devicemanagement.dtos.DeviceDetailsDTO;
import com.energyapp.devicemanagement.entities.AppUser;
import com.energyapp.devicemanagement.entities.Device;

public class DeviceBuilder {

    public static DeviceDTO toDeviceDTO(Device device) {
        return new DeviceDTO(device.getDescription(), device.getAddress(), device.getMaxConsumption(), device.getUserID());
    }

    public static DeviceDetailsDTO toDeviceDetailsDTO(Device device) {
        if(device.getUserID()!=null) {
            return new DeviceDetailsDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMaxConsumption(), device.getUserID().getUserID());
        }
        else return new DeviceDetailsDTO(device.getId(), device.getDescription(), device.getAddress(), device.getMaxConsumption(), null);
    }
    public static Device toEntity(DeviceDetailsDTO deviceDTO, AppUser user) {
        System.out.println("a intrat in to entity\n");
        return new Device(deviceDTO.getDescription(), deviceDTO.getAddress(), deviceDTO.getMaxConsumption(), user);
    }
}
