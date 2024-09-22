package com.jayway.payment_screenshot_processor_api.contract.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.payment_screenshot_processor_api.contract.dto.response.PaymentScreenshotResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PaymentScreenshotService {
    PaymentScreenshotResponse screenshotProcessor(MultipartFile multipartFile, String documentNumber) throws JsonProcessingException;
}
