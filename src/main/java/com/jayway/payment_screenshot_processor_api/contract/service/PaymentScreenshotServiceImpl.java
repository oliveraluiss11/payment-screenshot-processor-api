package com.jayway.payment_screenshot_processor_api.contract.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.payment_screenshot_processor_api.config.ObjectMapperConfig;
import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import com.jayway.payment_screenshot_processor_api.contract.dto.request.GetPaymentScreenshotRequest;
import com.jayway.payment_screenshot_processor_api.contract.dto.response.PaymentScreenshotResponse;
import com.jayway.payment_screenshot_processor_api.contract.dto.response.PaymentScreenshotResponseList;
import com.jayway.payment_screenshot_processor_api.contract.entity.PaymentScreenshotEntity;
import com.jayway.payment_screenshot_processor_api.contract.repository.PaymentScreenshotRepository;
import com.jayway.payment_screenshot_processor_api.contract.service.ocrmatcherstrategy.OcrMatcherStrategy;
import com.jayway.payment_screenshot_processor_api.exception.GenericClientException;
import com.jayway.payment_screenshot_processor_api.type.FileType;
import com.jayway.payment_screenshot_processor_api.util.EncryptionUtil;
import com.jayway.payment_screenshot_processor_api.util.FileUtil;
import com.jayway.payment_screenshot_processor_api.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static com.jayway.payment_screenshot_processor_api.constant.Constant.OPERATION_NUMBER_ALREADY_EXISTS;
import static com.jayway.payment_screenshot_processor_api.util.TesseractUtil.tesseractProcessor;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentScreenshotServiceImpl implements PaymentScreenshotService {
    private final List<OcrMatcherStrategy> ocrMatcherStrategies;
    private final PaymentScreenshotRepository paymentScreenshotRepository;
    private final EncryptionUtil encryptionUtil;

    @Override
    public PaymentScreenshotResponse screenshotProcessor(MultipartFile multipartFile, String documentNumber, String apiKey) throws JsonProcessingException {
        encryptionUtil.validateApiKey(apiKey);
        ValidationUtil.ensureIsNotEmpty(documentNumber, "Document number");
        ValidationUtil.ensureIsNotNull(multipartFile, "File");
        String fileExtension = getFileExtension(multipartFile);
        FileType.ensureValidExtension(fileExtension);
        InputStream fileConverted = FileUtil.convertMultiPartToInputStream(multipartFile);
        String resultTextImage = tesseractProcessor(fileConverted);
        log.info("Result Text Image: {}", resultTextImage);
        PaymentScreenshotProcessor processor = PaymentScreenshotProcessor.create(documentNumber);
        ocrMatcherStrategies
                .forEach(value -> value.apply(resultTextImage, processor));
        paymentScreenshotRepository.findByTransactionNumber(processor.getTransactionNumber())
                .ifPresent(value -> {
                    throw GenericClientException.create(OPERATION_NUMBER_ALREADY_EXISTS, HttpStatus.UNPROCESSABLE_ENTITY);
                });
        PaymentScreenshotEntity paymentScreenshotEntity = PaymentScreenshotEntity.from(processor);
        paymentScreenshotRepository.save(paymentScreenshotEntity);
        String processorLog = ObjectMapperConfig.getObjectMapper().writeValueAsString(processor);
        log.info("Result Processor: {}", processorLog);
        String encryptedResult = encryptionUtil.encrypt(processor);
        return PaymentScreenshotResponse.create(encryptedResult);
    }

    @Override
    public PaymentScreenshotResponseList getScreenshotProcessedList(String apiKey) {
        encryptionUtil.validateApiKey(apiKey);
        List<PaymentScreenshotEntity> entities = paymentScreenshotRepository.findAll();
        List<PaymentScreenshotResponse> processors = entities
                .stream()
                .map(PaymentScreenshotProcessor::from)
                .map(encryptionUtil::encrypt)
                .map(PaymentScreenshotResponse::create)
                .toList();
        return PaymentScreenshotResponseList.create(processors);
    }

    @Override
    public PaymentScreenshotProcessor getPaymentScreenshotByEncryptedValue(GetPaymentScreenshotRequest request, String apiKey) {
        encryptionUtil.validateApiKey(apiKey);
        request.ensureAttributes();
        return encryptionUtil.decrypt(request.getEncryptedPaymentScreenshot());
    }

    private String getFileExtension(MultipartFile file) {
        var originalFilename = file.getOriginalFilename();
        return Optional.ofNullable(originalFilename)
                .map(fileName -> fileName.lastIndexOf("."))
                .filter(lastDotIndex -> lastDotIndex > 0)
                .map(lastDotIndex -> originalFilename.substring(lastDotIndex + 1))
                .orElseThrow(() -> new IllegalArgumentException("El nombre de archivo original no debe ser nulo."));
    }
}
