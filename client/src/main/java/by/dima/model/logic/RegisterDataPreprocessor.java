package by.dima.model.logic;

import by.dima.model.entity.User;
import by.dima.util.ObjectConverter;

public class RegisterDataPreprocessor implements DataPreprocessor {
    private ObjectConverter converter;

    public RegisterDataPreprocessor(ObjectConverter converter) {
        this.converter = converter;
    }

    @Override
    public String process(String data) {
        String[] dataArray = data.split(" ");
        User user = new User(dataArray[1], dataArray[0]);

        return converter.write(user);
    }
}
