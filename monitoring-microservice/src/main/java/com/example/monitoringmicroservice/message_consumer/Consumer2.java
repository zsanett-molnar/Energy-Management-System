package com.example.monitoringmicroservice.message_consumer;

import com.example.monitoringmicroservice.dtos.DeviceJsonDTO;
import com.example.monitoringmicroservice.dtos.JsonDTO;
import com.example.monitoringmicroservice.entities.Device;
import com.example.monitoringmicroservice.services.DeviceService;
import com.example.monitoringmicroservice.services.MeasurementService;
import com.example.monitoringmicroservice.utils.Utils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.stereotype.Component;

@Component
public class Consumer2 {

    private final static String QUEUE_NAME = "device_update";
    private final DeviceService deviceService;

    public Consumer2(DeviceService service) throws Exception {
        this.deviceService = service;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("host.docker.internal");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(message);

            Utils utils = new Utils();
            DeviceJsonDTO dto = utils.processDeviceJson(message);
            //System.out.println(dto.toString());

            Integer deviceID = dto.getDeviceID();
            Integer userID = dto.getUserID();
            Integer maxConsumption = dto.getMaxConsumption();
            String operation = dto.getOperation();

            Device device = new Device(deviceID, maxConsumption, userID);
            System.out.println(device.toString());
            if(operation.equals("\"insert\"")) {
                deviceService.insert(device);
            }
            else if(operation.equals("\"update\"")){
                System.out.println("intra in if\n");
                deviceService.deleteByID(deviceID);
                deviceService.insert(device);
            }

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
