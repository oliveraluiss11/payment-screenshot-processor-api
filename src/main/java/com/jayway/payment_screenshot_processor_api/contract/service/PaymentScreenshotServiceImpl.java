package com.jayway.payment_screenshot_processor_api.contract.service;

import com.jayway.payment_screenshot_processor_api.contract.dto.response.PaymentScreenshotResponse;
import com.jayway.payment_screenshot_processor_api.util.ValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class PaymentScreenshotServiceImpl implements PaymentScreenshotService{
    @Override
    public PaymentScreenshotResponse screenshotProcessor(MultipartFile multipartFile, String documentNumber) {
        ValidationUtil.ensureIsNotEmpty(documentNumber, "Document number");
        ValidationUtil.ensureIsNotNull(multipartFile, "File");
        String encryptedResult = "2b7e151628aed2a6abf7158809cf4f3c";
        return PaymentScreenshotResponse.create(encryptedResult);
    }
}
