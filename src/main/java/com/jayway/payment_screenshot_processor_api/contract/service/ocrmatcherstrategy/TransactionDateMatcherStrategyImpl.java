package com.jayway.payment_screenshot_processor_api.contract.service.ocrmatcherstrategy;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jayway.payment_screenshot_processor_api.constant.Constant.UNKNOWN;

@Component
public class TransactionDateMatcherStrategyImpl implements OcrMatcherStrategy {

    @Override
    public void apply(String text, PaymentScreenshotProcessor processor) {
        String REGEX = "\\d{2} [a-zA-Z]{3} \\d{4} \\d{2}:\\d{2} [aApPmM]{2}|\\d{2} [a-z]{3}\\. \\d{4} - \\d{2}:\\d{2} [aApPmM]{2}";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        String result = Optional.of(matcher)
                .filter(Matcher::find)
                .map(Matcher::group)
                .orElse(UNKNOWN);
        processor.setTransactionDate(result);
    }
}