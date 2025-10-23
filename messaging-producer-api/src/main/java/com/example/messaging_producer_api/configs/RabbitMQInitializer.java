package com.example.messaging_producer_api.configs;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQInitializer implements ApplicationRunner {

    private final ConnectionFactory connectionFactory;

    public RabbitMQInitializer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void run(ApplicationArguments args) {
        try (var connection = this.connectionFactory.createConnection()) {

        }
    }
}