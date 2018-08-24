package by.dima.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JasonObjectConverter implements ObjectConverter {

    private ObjectMapper mapper;

    public JasonObjectConverter() {
        mapper = new ObjectMapper();
    }

    @Override
    public String write(Object obj) {
        String text = "";
        try {
            text = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return text;
    }

    @Override
    public <T> T read(String value, Class<T> type) {
        T result = null;

        try {
            result = mapper.readValue(value, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
