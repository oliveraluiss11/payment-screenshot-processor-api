package com.jayway.payment_screenshot_processor_api.contract.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SenderPayment implements Serializable {
    private String documentNumber;
    private String wallet;

    public static SenderPayment create(String documentNumber){
        return new SenderPayment(documentNumber, null);
    }
}
