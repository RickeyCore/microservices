package com.amigoscode.notification.rabbitmq;

import com.amigoscode.clients.fraud.NotificationRequest;
import com.amigoscode.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService service;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consume(NotificationRequest request) {
        log.info("Consumed {} from queue", request);
        service.sendNotification(request);
    }
}
