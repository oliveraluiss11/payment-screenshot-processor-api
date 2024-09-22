package com.jayway.payment_screenshot_processor_api.contract.dto.response;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PaymentScreenshotResponseList {
    private List<PaymentScreenshotResponse> screenshots;

    public static PaymentScreenshotResponseList create(List<PaymentScreenshotResponse> screenshots){
        return new PaymentScreenshotResponseList(screenshots);
    }
}
