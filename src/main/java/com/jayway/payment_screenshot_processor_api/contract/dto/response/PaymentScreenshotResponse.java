package com.jayway.payment_screenshot_processor_api.contract.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentScreenshotResponse {
    private String encryptedResult;

    public static PaymentScreenshotResponse create(String encryptedResult){
        return new PaymentScreenshotResponse(encryptedResult);
    }
}
