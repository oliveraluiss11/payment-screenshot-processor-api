package com.jayway.payment_screenshot_processor_api.contract.service.ocrmatcherstrategy;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jayway.payment_screenshot_processor_api.constant.Constant.PEN_CODE;
import static com.jayway.payment_screenshot_processor_api.constant.Constant.UNKNOWN;

@Component
public class AmountMatcherStrategyImpl implements OcrMatcherStrategy {

    @Override
    public void apply(String text, PaymentScreenshotProcessor processor) {
        String REGEX = "S/\\s*\\d+\\.\\d{2}|\\d+";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);
        String result = Optional.of(matcher)
                .filter(Matcher::find)
                .map(Matcher::group)
                .orElse(UNKNOWN);
        List<String> valueList = List.of(result.split("\\s+"));
        BigDecimal amount = BigDecimal.ZERO;
        String currencyCode;
        int count = valueList.size();
        currencyCode = PEN_CODE;
        amount = switch (count) {
            case 1 -> new BigDecimal(valueList.getFirst());
            case 2 -> new BigDecimal(valueList.get(1));
            default -> amount;
        };
        processor.setAmount(amount);
        processor.setCurrencyCode(currencyCode);
    }
}
