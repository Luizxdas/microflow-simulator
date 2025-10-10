package com.example.messaging_producer_api.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange.files");
    }

    @Bean
    public Queue queueText() {
        return new Queue("queue.text");
    }

    @Bean
    public Queue queueImage() {
        return new Queue("queue.images");
    }

    @Bean
    public Binding bindingTexts(Queue queueText, TopicExchange exchange) {
        return BindingBuilder.bind(queueText).to(exchange).with("file.text");
    }

    @Bean
    public Binding bidingImages(Queue queueImage, TopicExchange exchange) {
        return BindingBuilder.bind(queueImage).to(exchange).with("file.image");
    }

}
