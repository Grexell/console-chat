package by.dima.model.logic.preprocessor.impl;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.model.logic.preprocessor.DataPreprocessor;
import by.dima.util.ObjectConverter;
import by.dima.util.UserHolder;

public class RegisterDataPreprocessor implements DataPreprocessor {
    private ObjectConverter converter;

    private static final String CLIENT = "client";
    private static final String AGENT = "agent";

    public RegisterDataPreprocessor(ObjectConverter converter) {
        this.converter = converter;
    }

    @Override
    public String parse(String data) {
        String[] dataArray = data.split(" ");

        User user;
        if (CLIENT.equalsIgnoreCase(dataArray[0])){
            user = new User(dataArray[1], Role.CLIENT);
        } else if (AGENT.equalsIgnoreCase(dataArray[0])){
            user = new User(dataArray[1], Role.AGENT);
        } else {
           throw new IllegalArgumentException("Invalid user role");
        }

        UserHolder.getInstance().setCurrentUser(user);

        return converter.write(user);
    }
}
