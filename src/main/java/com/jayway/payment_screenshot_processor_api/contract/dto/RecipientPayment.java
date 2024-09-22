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
public class RecipientPayment implements Serializable {
    private String cellphone;
    private String wallet;

    public static RecipientPayment create(){
        return new RecipientPayment(null, null);
    }
}
