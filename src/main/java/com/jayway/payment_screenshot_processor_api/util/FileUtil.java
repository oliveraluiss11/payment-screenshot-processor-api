package com.jayway.payment_screenshot_processor_api.util;

import com.jayway.payment_screenshot_processor_api.exception.GenericClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileUtil {
    public static InputStream convertMultiPartToInputStream(MultipartFile file) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (var inputStream = file.getInputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
            }

            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException exception) {
            throw GenericClientException.create(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
