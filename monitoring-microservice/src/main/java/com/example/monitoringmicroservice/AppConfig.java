package com.example.monitoringmicroservice;

import com.example.monitoringmicroservice.message_consumer.Consumer;
import com.example.monitoringmicroservice.repositories.MeasurementRepoImplement;
import com.example.monitoringmicroservice.repositories.MeasurementRepository;
import com.example.monitoringmicroservice.services.DeviceService;
import com.example.monitoringmicroservice.services.MeasurementService;
import com.example.monitoringmicroservice.websocket.WebSocketController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class AppConfig {

    @Bean
    public MeasurementRepository measurementRepository2() {
        return new MeasurementRepoImplement();
    }

    @Bean
    public MeasurementService meausrementService(MeasurementRepository measurementRepository) {
        return new MeasurementService(measurementRepository);
    }

    @Bean
    public WebSocketController controller(SimpMessagingTemplate template) {
        return new WebSocketController(template);
    }

    @Bean
    public Consumer consumer(MeasurementService meausrementService, DeviceService deviceService, WebSocketController controller) throws Exception {
        return new Consumer(meausrementService, deviceService, controller);
    }
}
