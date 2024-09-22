package com.jayway.payment_screenshot_processor_api.exception;

import com.jayway.payment_screenshot_processor_api.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class GenericClientException extends RuntimeException{
    private HttpStatus httpStatus;
    private LocalDateTime registrationDate;

    public GenericClientException(String message, HttpStatus httpStatus, LocalDateTime registrationDate){
        super(message);
        this.httpStatus = httpStatus;
        this.registrationDate = registrationDate;
    }
    public static GenericClientException create(String message, HttpStatus httpStatus){
        return new GenericClientException(message, httpStatus, DateUtil.getLocalDateTime());
    }
}
