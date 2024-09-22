package com.jayway.payment_screenshot_processor_api.contract.service.ocrmatcherstrategy;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        processor.getRecipient().setWallet(result);
    }
}