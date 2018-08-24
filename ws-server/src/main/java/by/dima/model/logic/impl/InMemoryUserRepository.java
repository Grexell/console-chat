package by.dima.model.logic.impl;

import by.dima.model.entity.User;
import by.dima.model.logic.MessageSender;
import by.dima.model.logic.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private static final String USER_REGISTER_MESSAGE = "User Registered";
    private static final String USER_UNREGISTER_MESSAGE = "User unregistered";

    private Map<User, MessageSender> users;

    public InMemoryUserRepository() {
        users = new ConcurrentHashMap<>();
    }

    public InMemoryUserRepository(Map<User, MessageSender> users) {
        this.users = users;
    }

    @Override
    public void add(User user, MessageSender messageSender) {
        users.put(user, messageSender);
        LOG.info(USER_REGISTER_MESSAGE);
    }

    @Override
    public MessageSender get(User user) {
        return users.get(user);
    }

    @Override
    public void remove(User user) {
        users.remove(user);
        LOG.info(USER_UNREGISTER_MESSAGE);
    }

    @Override
    public boolean isRegistered(User user) {
        return users.containsKey(user);
    }
}
