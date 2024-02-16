package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import com.fasterxml.jackson.databind.ObjectMapper;
//rabbitmq
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Main {
    private final static String QUEUE_NAME = "measurements";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
                BufferedReader reader = new BufferedReader(new FileReader("sensor.csv"));
                BufferedReader id_reader = new BufferedReader(new FileReader("id.txt"))) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Integer deviceID = Integer.parseInt(id_reader.readLine().trim());

            String line;
            while ((line = reader.readLine()) != null) {
                Long millis = System.currentTimeMillis();
                Float measurementValue = Float.parseFloat(line.trim());

                JsonDTO dto = new JsonDTO(millis, deviceID, measurementValue);
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(dto);
                channel.basicPublish("", QUEUE_NAME, null, json.getBytes());

                // msj = "Timestamp: " +
                // Instant.ofEpochMilli(Long.parseLong(timestamp)).atZone(ZoneOffset.UTC).toLocalDateTime()
                // + " Value: " + measurementValue;

                System.out.println(" [x] Sent '" + json + "'");

                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}