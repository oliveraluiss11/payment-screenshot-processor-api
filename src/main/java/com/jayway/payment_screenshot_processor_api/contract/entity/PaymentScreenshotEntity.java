package com.jayway.payment_screenshot_processor_api.contract.entity;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import com.jayway.payment_screenshot_processor_api.contract.dto.RecipientPayment;
import com.jayway.payment_screenshot_processor_api.contract.dto.SenderPayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "screenshots")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentScreenshotEntity implements Serializable {
    @Id
    private String paymentScreenshotId;
    private BigDecimal amount;
    private String currencyCode;
    private String transactionNumber;
    private String transactionDate;
    private RecipientPayment recipient;
    private SenderPayment sender;
    private LocalDateTime registrationDate;

    public static PaymentScreenshotEntity from(PaymentScreenshotProcessor processor) {
        return new PaymentScreenshotEntity(null,
                processor.getAmount(),
                processor.getCurrencyCode(),
                processor.getTransactionNumber(),
                processor.getTransactionDate(),
                processor.getRecipient(),
                processor.getSender(),
                processor.getRegistrationDate());
    }
}
