package com.jayway.payment_screenshot_processor_api.util;

import com.jayway.payment_screenshot_processor_api.constant.Constant;
import com.jayway.payment_screenshot_processor_api.exception.GenericClientException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TesseractUtil {
    public static String tesseractProcessor(File file){
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(Constant.RESOURCES_TESSDATA);
        tesseract.setLanguage("spa");
        tesseract.setPageSegMode(Constant.ONE);
        tesseract.setOcrEngineMode(Constant.ONE);
        try {
            return tesseract.doOCR(file);
        } catch (TesseractException e) {
            throw GenericClientException.create(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    public static String tesseractProcessor(InputStream inputStream){
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(Constant.RESOURCES_TESSDATA);
        tesseract.setLanguage("spa");
        tesseract.setPageSegMode(Constant.ONE);
        tesseract.setOcrEngineMode(Constant.ONE);
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return tesseract.doOCR(bufferedImage);
        } catch (IOException | TesseractException e) {
            throw GenericClientException.create(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
