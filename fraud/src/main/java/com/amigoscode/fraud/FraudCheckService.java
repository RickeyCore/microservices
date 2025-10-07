package com.amigoscode.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckRepository fraudCheckRepository;

    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .createdAd(LocalDateTime.now())
                        .isFraudster(false)
                        .build()
        );
        return false;
    }
}
