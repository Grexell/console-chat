package by.dima.model.logic.repository;

import by.dima.model.entity.User;

import java.util.List;

public interface UserRepository {
    void add(User user);
    void remove(User user);
    User getById(String id);
    boolean isRegistered(User user);

    List<User> getAllUsers();
}