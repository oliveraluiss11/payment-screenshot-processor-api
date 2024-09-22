package com.jayway.payment_screenshot_processor_api.contract.service.ocrmatcherstrategy;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jayway.payment_screenshot_processor_api.constant.Constant.EMPTY;
import static com.jayway.payment_screenshot_processor_api.constant.Constant.UNKNOWN;

@Component
public class RecipientWalletMatcherStrategyImpl implements OcrMatcherStrategy {
    private final String REGEX = "Destino:\\s*\\n*\\s*(\\w+)";

    @Override
    public void apply(String text, PaymentScreenshotProcessor processor) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        String result = Optional.of(matcher)
                .filter(Matcher::find)
                .map(Matcher::group)
                .orElse(UNKNOWN);
        List<String> resultList = Arrays.asList(result.split("\\s"));
        result = resultList.stream()
                .filter(value -> !EMPTY.equals(value))
                .filter(value -> !"Destino:".equals(value))
                .findFirst()
                .orElse(UNKNOWN);
        result = result.toUpperCase();
        processor.getRecipient().setWallet(result);
    }
}