package com.example.monitoringmicroservice;

import com.example.monitoringmicroservice.message_consumer.Consumer;
import com.example.monitoringmicroservice.message_consumer.Consumer2;
import com.example.monitoringmicroservice.repositories.DeviceRepoImpl;
import com.example.monitoringmicroservice.repositories.DeviceRepository;
import com.example.monitoringmicroservice.repositories.MeasurementRepoImplement;
import com.example.monitoringmicroservice.repositories.MeasurementRepository;
import com.example.monitoringmicroservice.services.DeviceService;
import com.example.monitoringmicroservice.services.MeasurementService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigConsumer2 {

    @Bean
    public DeviceRepository deviceRepository2() {
        return new DeviceRepoImpl();
    }

    @Bean
    public DeviceService deviceService(DeviceRepository deviceRepository) {
        return new DeviceService(deviceRepository);
    }

    @Bean
    public Consumer2 consumer2(DeviceService deviceService) throws Exception {
        return new Consumer2(deviceService);
    }
}
