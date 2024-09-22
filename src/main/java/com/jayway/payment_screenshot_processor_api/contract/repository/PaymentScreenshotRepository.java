package com.jayway.payment_screenshot_processor_api.contract.repository;

import com.jayway.payment_screenshot_processor_api.contract.entity.PaymentScreenshotEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentScreenshotRepository extends MongoRepository<PaymentScreenshotEntity, String> {
    Optional<PaymentScreenshotEntity> findByTransactionNumber(String transactionNumber);
}
