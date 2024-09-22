package com.jayway.payment_screenshot_processor_api.util;

import com.jayway.payment_screenshot_processor_api.contract.dto.PaymentScreenshotProcessor;
import com.jayway.payment_screenshot_processor_api.contract.entity.PaymentScreenshotEntity;
import com.jayway.payment_screenshot_processor_api.exception.GenericClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import static com.jayway.payment_screenshot_processor_api.constant.Constant.INVALID_API_KEY;

@Component
@RequiredArgsConstructor
public class EncryptionUtil {
    public static final String AES = "AES";
    public static final String OCR_KEY = "ocr.key";
    public static final String API_KEY = "api.key";
    private final Environment environment;

    public String encrypt(PaymentScreenshotProcessor processor) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        Cipher cipher;
        SecretKey secretKey = getSecretKey();
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(processor);
            byte[] objectBytes = outputStream.toByteArray();
            cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(objectBytes);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            throw GenericClientException.create(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public PaymentScreenshotProcessor decrypt(String encryptedText) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decryptedBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (PaymentScreenshotProcessor) objectInputStream.readObject();
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException | ClassNotFoundException e) {
            throw GenericClientException.create(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    public void validateApiKey(String apiKey) {
        String apiKeyEnvironment = environment.getProperty(API_KEY);
        Optional.ofNullable(apiKey)
                .filter(value -> value.equals(apiKeyEnvironment))
                .orElseThrow(() -> GenericClientException.create(INVALID_API_KEY, HttpStatus.UNAUTHORIZED));
    }

    private SecretKey getSecretKey() {
        String key = environment.getProperty(OCR_KEY);
        byte[] decode = Base64.getDecoder().decode(key);
        return new SecretKeySpec(decode, AES);
    }
}
