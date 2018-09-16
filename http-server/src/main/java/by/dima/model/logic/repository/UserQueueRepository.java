package by.dima.model.logic.repository;

import by.dima.model.entity.User;

import java.util.List;

public interface UserQueueRepository {
    void add(User user);
    void remove(User user);
    User get();
    boolean hasUser();

    List<User> getAllUsers();
}