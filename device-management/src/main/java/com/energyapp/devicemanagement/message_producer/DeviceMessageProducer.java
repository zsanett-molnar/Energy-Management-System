package com.energyapp.devicemanagement.message_producer;

import com.energyapp.devicemanagement.dtos.builders.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class DeviceMessageProducer {

    private final static String QUEUE_NAME = "device_update";

    public DeviceMessageProducer() {
    }

    public void SendMessage(Integer deviceID, Integer maxConsumption,
                            Integer userID, String operation) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("host.docker.internal");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            MessageDTO dto = new MessageDTO(deviceID,userID,maxConsumption,operation);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(dto);
            channel.basicPublish("", QUEUE_NAME, null, json.getBytes());
            System.out.println(" [x] Sent '" + json + "'");

        }
    }

}
