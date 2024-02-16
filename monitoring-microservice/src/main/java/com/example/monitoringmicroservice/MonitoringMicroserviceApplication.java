package com.example.monitoringmicroservice;

import com.example.monitoringmicroservice.message_consumer.Consumer;
import com.example.monitoringmicroservice.repositories.MeasurementRepository;
import com.example.monitoringmicroservice.services.MeasurementService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MonitoringMicroserviceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MonitoringMicroserviceApplication.class, args);
//
//		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//		MeasurementRepository measurementRepository = context.getBean(MeasurementRepository.class);
//
//		Consumer consumer = new Consumer(new MeasurementService(measurementRepository));

		//MeasurementService measurementService;

	}

}
