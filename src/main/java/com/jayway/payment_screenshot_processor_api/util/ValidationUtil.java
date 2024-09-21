package com.jayway.payment_screenshot_processor_api.util;

import com.jayway.payment_screenshot_processor_api.constant.Constant;
import com.jayway.payment_screenshot_processor_api.exception.GenericClientException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class ValidationUtil {
    public static void ensureIsNotNull(Object object, String attribute){
        String message = String.format(Constant.NOT_NULL_MESSAGE, attribute);
        Optional.ofNullable(object).orElseThrow(()-> GenericClientException.create(message, HttpStatus.BAD_REQUEST));
    }

    public static void ensureIsNotEmpty(String text, String attribute){
        String message = String.format(Constant.NOT_EMPTY_MESSAGE, attribute);
        Optional.ofNullable(text).filter(value -> !Constant.EMPTY.equals(text)).orElseThrow(()-> GenericClientException.create(message, HttpStatus.BAD_REQUEST));
    }
}
