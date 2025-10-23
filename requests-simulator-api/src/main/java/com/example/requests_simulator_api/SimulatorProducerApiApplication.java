package com.example.requests_simulator_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimulatorProducerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulatorProducerApiApplication.class, args);
	}

}
