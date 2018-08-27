package by.dima.model.logic;

import by.dima.model.entity.Message;
import by.dima.model.entity.User;

public interface MessageRepository {
    void sendMessage(User user, Message message);
    Message getMessage(User user);
    boolean hasMessage(User user);
}
