package by.dima.model.logic;

import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;
import by.dima.util.UserHolder;

public class UnregisterDataPreprocessor implements DataPreprocessor {
    private ObjectConverter converter;

    public UnregisterDataPreprocessor() {
        this(new JasonObjectConverter());
    }

    public UnregisterDataPreprocessor(ObjectConverter converter) {
        this.converter = converter;
    }

    @Override
    public String process(String data) {
        data = converter.write(UserHolder.getInstance().getCurrentUser());
        UserHolder.getInstance().setCurrentUser(null);
        return data;
    }
}
