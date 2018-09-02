package by.dima.model.logic.repository.impl;

import by.dima.model.entity.User;
import by.dima.model.logic.repository.UserQueueRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class InMemoryUserQueueRepository implements UserQueueRepository {

    private BlockingQueue<User> users;

    public InMemoryUserQueueRepository() {
        users = new LinkedBlockingQueue<>();
    }

    public InMemoryUserQueueRepository(BlockingQueue<User> users) {
        this.users = users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    @Override
    public User get() {
        return users.poll();
    }

    @Override
    public boolean hasUser() {
        return !users.isEmpty();
    }
}
