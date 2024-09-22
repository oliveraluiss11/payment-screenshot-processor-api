package com.jayway.payment_screenshot_processor_api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperConfig {
    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Registrar el módulo para manejar Java 8 Date/Time API (como LocalDateTime)
        objectMapper.registerModule(new JavaTimeModule());

        // Evitar que Jackson intente serializar LocalDateTime como timestamps
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Configurar un formato de fecha estándar (opcional, si deseas un formato específico)
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true)); // o usa SimpleDateFormat si prefieres

        return objectMapper;
    }
}
