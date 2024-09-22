package com.jayway.payment_screenshot_processor_api.type;

import com.jayway.payment_screenshot_processor_api.constant.Constant;
import com.jayway.payment_screenshot_processor_api.exception.GenericClientException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum FileType {
    JPG("JPG", "jpg"),
    PNG("PNG", "png"),
    JPEG("JPEG", "jpeg");

    private final String code;
    private final String extension;

    public static List<FileType> getFileTypeList() {
        return Stream.of(FileType.values()).toList();
    }

    public static List<String> getFileExtensionList() {
        return getFileTypeList()
                .stream()
                .map(FileType::getExtension)
                .toList();
    }

    public static void ensureValidExtension(String extension){
        String message = String.format(Constant.NOT_FOUND_MESSAGE, extension);
        getFileExtensionList().stream()
                .filter(value -> value.equals(extension))
                .findFirst()
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.BAD_REQUEST));
    }
}
