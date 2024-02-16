package com.example.chatmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ChatMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatMicroserviceApplication.class, args);
	}

}
