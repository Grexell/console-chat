package by.dima.model.logic;

import by.dima.model.entity.User;

public interface UserQueueService {
    void add(User user);
    User get();
    boolean hasUser();
}