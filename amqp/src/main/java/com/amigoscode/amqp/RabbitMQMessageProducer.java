package com.amigoscode.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publish(Object message, String exchange, String routingKey) {
        log.info("Publishing message {} to exchange {} with routingKey {}", message, exchange, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Published message {} to exchange {} with routingKey {}", message, exchange, routingKey);
    }
}
