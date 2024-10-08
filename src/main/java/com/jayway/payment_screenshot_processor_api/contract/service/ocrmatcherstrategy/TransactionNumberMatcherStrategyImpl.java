package com.jayway.payment_screenshot_processor_api.contract.service.ocrmatcherstrategy;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jayway.payment_screenshot_processor_api.constant.Constant.UNKNOWN;

@Component
public class TransactionNumberMatcherStrategyImpl implements OcrMatcherStrategy {

    @Override
    public void apply(String text, PaymentScreenshotProcessor processor) {
        String REGEX = "\\b\\d{8}\\b";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        String result = Optional.of(matcher)
                .filter(Matcher::find)
                .map(Matcher::group)
                .orElse(UNKNOWN);
        processor.setTransactionNumber(result);
    }
}