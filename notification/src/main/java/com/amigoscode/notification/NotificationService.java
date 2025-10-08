package com.amigoscode.notification;

import com.amigoscode.clients.fraud.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest request) {
        Notification notification = Notification.builder()
                .toCustomerEmail(request.toCustomerEmail())
                .message(request.message())
                .toCustomerId(request.toCustomerId())
                .sentAt(LocalDateTime.now())
                .sender("Rickey")
                .build();
        notificationRepository.save(notification);
    }
}
