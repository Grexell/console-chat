package by.dima.model.logic;

import by.dima.model.entity.User;
import by.dima.model.logic.MessageSender;

public interface UserRepository {
    void add(User user);
    void remove(User user);
    User getById(String id);
    boolean isRegistered(User user);
}