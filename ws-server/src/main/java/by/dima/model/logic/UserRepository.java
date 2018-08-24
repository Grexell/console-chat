package by.dima.model.logic;

import by.dima.model.entity.User;
import by.dima.model.logic.MessageSender;

public interface UserRepository {
    void add(User user, MessageSender messageSender);
    MessageSender get(User user);
    void remove(User user);
    boolean isRegistered(User user);
}