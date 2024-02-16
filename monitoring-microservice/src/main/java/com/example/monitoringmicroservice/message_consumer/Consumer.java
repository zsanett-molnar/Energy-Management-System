package com.example.monitoringmicroservice.message_consumer;
//rabbitmq

import com.example.monitoringmicroservice.dtos.JsonDTO;
import com.example.monitoringmicroservice.entities.Device;
import com.example.monitoringmicroservice.entities.Measurement;
import com.example.monitoringmicroservice.services.DeviceService;
import com.example.monitoringmicroservice.services.MeasurementService;
import com.example.monitoringmicroservice.utils.Utils;

import com.example.monitoringmicroservice.websocket.WebSocketController;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


@Component
public class Consumer {
    private final static String QUEUE_NAME = "measurements";
    private final MeasurementService measurementService;
    private final DeviceService deviceService;

    private final WebSocketController controller;


    public Consumer(MeasurementService service, DeviceService deviceService, WebSocketController controller) throws Exception {
        this.measurementService=service;
        this.deviceService= deviceService;
        this.controller=controller;


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("host.docker.internal");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        AtomicReference<Float> valueSum = new AtomicReference<>(0.0F);
        AtomicReference<Integer> contor = new AtomicReference<>(0);
        AtomicReference<Integer> daycontor = new AtomicReference<>(0);
        AtomicReference<Integer> hour = new AtomicReference<>(-1);
        AtomicReference<Boolean> dayPassed = new AtomicReference<>(false);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            contor.getAndSet(contor.get() + 1);
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Primit: " + message);
            Utils utils = new Utils();
            JsonDTO dto = utils.processJson(message);


            Timestamp timestamp = new Timestamp(dto.getTimestamp());

            if(contor.get()==1) {
                hour.set(utils.getHourFromTimestamp(timestamp));
            }
            Float value = dto.getMeasurement();
            Integer deviceID = dto.getDeviceID();

            valueSum.updateAndGet(v -> v + value);

            if (contor.get()%6==0) {
                Optional<Device> device = deviceService.getByID(deviceID);

                if(dayPassed.get()) {
                    timestamp = utils.incrementDay(timestamp, daycontor.get());

                }
                Measurement measurement = new Measurement(timestamp, hour.get(),device.get(),valueSum.get()/6);
                //apelam metoda de save din service
                measurementService.insert(measurement);

                //controller.sendNotificationToFrontend("SALOOOOT");
                if(measurement.getDeviceID().getMaxConsumption() < valueSum.get()/6) {
                   // webSocketHandler.handleMessage();

                    controller.sendNotificationToFrontend("ATENTIE " + deviceID);
                }

                if(hour.get() == 23) {
                    hour.getAndSet(0);
                    dayPassed.getAndSet(true);
                    daycontor.getAndSet(daycontor.get()+1);
                }
                else {
                    hour.getAndSet(hour.get() + 1);
                }

                valueSum.set(0.0F);
                System.out.println(valueSum.get());
                System.out.println();
            }

            //Measurement measurement = new Measurement(timestamp,hour,deviceID,valueSum.get().floatValue());
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }



}
