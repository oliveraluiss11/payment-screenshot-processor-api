package com.jayway.payment_screenshot_processor_api.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SenderPayment {
    private String documentNumber;
    private String wallet;

    public static SenderPayment create(String documentNumber){
        return new SenderPayment(documentNumber, null);
    }
}
