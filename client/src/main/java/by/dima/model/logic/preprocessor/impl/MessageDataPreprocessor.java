package by.dima.model.logic.preprocessor.impl;

import by.dima.model.entity.Message;
import by.dima.model.logic.preprocessor.DataPreprocessor;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;
import by.dima.util.UserHolder;

import java.util.Date;

public class MessageDataPreprocessor implements DataPreprocessor {
    private ObjectConverter converter;

    public MessageDataPreprocessor() {
        this(new JasonObjectConverter());
    }

    public MessageDataPreprocessor(ObjectConverter converter) {
        this.converter = converter;
    }

    @Override
    public String parse(String data) {
        data = converter.write(new Message(data, new Date(), UserHolder.getInstance().getCurrentUser()));
        return data;
    }
}
