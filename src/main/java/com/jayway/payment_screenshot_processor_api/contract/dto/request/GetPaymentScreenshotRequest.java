package com.jayway.payment_screenshot_processor_api.contract.dto.request;

import com.jayway.payment_screenshot_processor_api.util.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetPaymentScreenshotRequest {
    private String encryptedPaymentScreenshot;

    public void ensureAttributes(){
        ValidationUtil.ensureIsNotEmpty(encryptedPaymentScreenshot, "Encrypted payment screenshot");
    }
}
