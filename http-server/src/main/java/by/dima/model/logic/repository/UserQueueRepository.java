package by.dima.model.logic.repository;

import by.dima.model.entity.User;

public interface UserQueueRepository {
    void add(User user);
    void remove(User user);
    User get();
    boolean hasUser();
}