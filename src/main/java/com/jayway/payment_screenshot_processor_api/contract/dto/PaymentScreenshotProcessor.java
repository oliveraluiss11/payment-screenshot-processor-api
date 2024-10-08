package com.jayway.payment_screenshot_processor_api.contract.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jayway.payment_screenshot_processor_api.contract.entity.PaymentScreenshotEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentScreenshotProcessor implements Serializable {
    private BigDecimal amount;
    private String currencyCode;
    private String transactionNumber;
    private String transactionDate;
    private RecipientPayment recipient;
    private SenderPayment sender;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime registrationDate;

    public static PaymentScreenshotProcessor create(String senderDocumentNumber) {
        RecipientPayment recipientPayment = RecipientPayment.create();
        SenderPayment senderPayment = SenderPayment.create(senderDocumentNumber);
        return new PaymentScreenshotProcessor(null, null, null, null, recipientPayment, senderPayment, LocalDateTime.now());
    }

    public static PaymentScreenshotProcessor from(PaymentScreenshotEntity entity) {
        return new PaymentScreenshotProcessor(entity.getAmount(), entity.getCurrencyCode(), entity.getTransactionNumber(), entity.getTransactionDate(), entity.getRecipient(), entity.getSender(), entity.getRegistrationDate());
    }
}
