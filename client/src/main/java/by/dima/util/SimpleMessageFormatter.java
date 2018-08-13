package by.dima.util;

import by.dima.model.entity.Message;

public class SimpleMessageFormatter implements MessageFormatter {
    public static final String DELIMETER = " | ";

    @Override
    public String format(Message message) {
        return message.getDate() + DELIMETER + message.getUser().getUsername() + DELIMETER + message.getText();
    }
}
