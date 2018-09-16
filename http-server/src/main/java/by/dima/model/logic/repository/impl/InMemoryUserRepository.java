package by.dima.model.logic.repository.impl;

import by.dima.model.entity.User;
import by.dima.model.logic.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private static final String USER_REGISTER_MESSAGE = "User Registered";
    private static final String USER_UNREGISTER_MESSAGE = "User unregistered";

    private Map<String, User> users;

    public InMemoryUserRepository() {
        users = new ConcurrentHashMap<>();
    }

    public InMemoryUserRepository(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public void add(User user) {
        users.put(user.getId(), user);
        LOG.info(USER_REGISTER_MESSAGE);
    }

    @Override
    public void remove(User user) {
        users.remove(user.getId());
        LOG.info(USER_UNREGISTER_MESSAGE);
    }

    @Override
    public User getById(String id) {
        return users.get(id);
    }

    @Override
    public boolean isRegistered(User user) {
        return users.containsKey(user.getId());
    }

    @Override
    public List<User> getAllUsers() {
        return new LinkedList<>(users.values());
    }
}
