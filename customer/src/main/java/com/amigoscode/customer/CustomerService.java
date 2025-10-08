package com.amigoscode.customer;

import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.fraud.NotificationClient;
import com.amigoscode.clients.fraud.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);

        var fraudResponse = fraudClient.isFraudster(customer.getId());
        notificationClient.sendNotification(NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message("Hello World")
                .build());
        if (fraudResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }
}
