package com.jayway.payment_screenshot_processor_api.contract.service.ocrmatcherstrategy;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.jayway.payment_screenshot_processor_api.constant.Constant.UNKNOWN;

@Component
public class SenderWalletMatcherStrategyImpl implements OcrMatcherStrategy {
    private final Map<String, List<String>> PATTERNS_MAP = Map.of(
            "PLIN", List.of("¡Pago exitoso!", "CJ Interbank"),
            "YAPE", List.of("¡Yapeaste!", "Estimating resolution as")
    );

    @Override
    public void apply(String text, PaymentScreenshotProcessor processor) {
        String result = getFirst(text).orElse(UNKNOWN);
        processor.getSender().setWallet(result);
    }

    private Optional<String> getFirst(String text) {
        return PATTERNS_MAP.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(text::contains))
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
