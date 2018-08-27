package by.dima.model.logic.impl;

import by.dima.model.entity.User;
import by.dima.model.logic.UserQueueService;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class InMemoryUserQueueService implements UserQueueService  {

    private BlockingQueue<User> users;

    public InMemoryUserQueueService() {
        users = new LinkedBlockingQueue<>();
    }

    public InMemoryUserQueueService(BlockingQueue<User> users) {
        this.users = users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User get() {
        return users.poll();
    }

    @Override
    public boolean hasUser() {
        return users.isEmpty();
    }
}
