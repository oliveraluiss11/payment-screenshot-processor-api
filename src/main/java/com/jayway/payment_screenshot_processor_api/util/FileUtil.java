package com.jayway.payment_screenshot_processor_api.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileUtil {
    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return convertedFile;
    }
}
