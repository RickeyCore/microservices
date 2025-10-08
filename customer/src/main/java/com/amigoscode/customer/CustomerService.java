package com.amigoscode.customer;

import com.amigoscode.clients.fraud.FraudClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);

        var fraudResponse = fraudClient.isFraudster(customer.getId());

        if (fraudResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }
}
