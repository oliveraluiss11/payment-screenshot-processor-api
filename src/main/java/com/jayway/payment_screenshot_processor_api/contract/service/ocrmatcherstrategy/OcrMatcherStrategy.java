package com.jayway.payment_screenshot_processor_api.contract.service.ocrmatcherstrategy;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;

public interface OcrMatcherStrategy {
    void apply(String text, PaymentScreenshotProcessor processor);
}
