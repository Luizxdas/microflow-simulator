package com.example.messaging_producer_api;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessagingProducerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingProducerApiApplication.class, args);
	}

}
