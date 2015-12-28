package org.raptor.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.io.IOException;

/**
 * Created by Anant on 22-12-2015.
 */
public class JacksonJSON implements IJSON {
    private final Logger logger  = LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper;

    public JacksonJSON()
    {
        objectMapper = new ObjectMapper();
    }

    @Override
    public <T> T getInstance(String jsonString, Class<T> type) {
        T value = null;

        try {
            value = objectMapper.readValue(jsonString, type);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }

        return value;
    }

    @Override
    public <T> String getJsonString(T object) {
        String value = null;

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            logger.error(exception.getMessage());
        }

        return value;
    }
}
