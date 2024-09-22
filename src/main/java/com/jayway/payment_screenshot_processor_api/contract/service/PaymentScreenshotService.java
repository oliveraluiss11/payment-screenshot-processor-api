package com.jayway.payment_screenshot_processor_api.contract.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import com.jayway.payment_screenshot_processor_api.contract.dto.request.GetPaymentScreenshotRequest;
import com.jayway.payment_screenshot_processor_api.contract.dto.response.PaymentScreenshotResponse;
import com.jayway.payment_screenshot_processor_api.contract.dto.response.PaymentScreenshotResponseList;
import org.springframework.web.multipart.MultipartFile;

public interface PaymentScreenshotService {
    PaymentScreenshotResponse screenshotProcessor(MultipartFile multipartFile, String documentNumber, String apiKey) throws JsonProcessingException;
    PaymentScreenshotResponseList getScreenshotProcessedList(String apiKey);
    PaymentScreenshotProcessor getPaymentScreenshotByEncryptedValue(GetPaymentScreenshotRequest request, String apiKey);
}
