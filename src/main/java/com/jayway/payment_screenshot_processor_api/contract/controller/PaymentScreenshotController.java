package com.jayway.payment_screenshot_processor_api.contract.controller;

import com.jayway.payment_screenshot_processor_api.contract.dto.ErrorDTO;
import com.jayway.payment_screenshot_processor_api.contract.dto.response.PaymentScreenshotResponse;
import com.jayway.payment_screenshot_processor_api.contract.service.PaymentScreenshotService;
import com.jayway.payment_screenshot_processor_api.exception.GenericClientException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentScreenshotController {
    private final PaymentScreenshotService paymentScreenshotService;

    @PostMapping("/screenshots")
    public ResponseEntity<PaymentScreenshotResponse> processingScreenshot(@RequestParam("file") MultipartFile file,
                                                                          @RequestParam("documentNumber") String documentNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentScreenshotService.screenshotProcessor(file, documentNumber));
    }
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ErrorDTO> handleMissingParams(
            MissingServletRequestPartException exception,
            HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDTO> handleMissingParams(
            MissingServletRequestParameterException exception,
            HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericClientException.class)
    public ResponseEntity<ErrorDTO> handleGenericClientException(
            GenericClientException exception,
            HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception, HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
