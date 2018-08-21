package by.dima.model.logic.impl;

import by.dima.model.entity.User;
import by.dima.model.logic.AgentService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InMemoryAgentService implements AgentService {
    private BlockingQueue<User> userQueue;

    public InMemoryAgentService() {
        userQueue = new LinkedBlockingQueue<>();
    }

    public InMemoryAgentService(BlockingQueue<User> userQueue) {
        this.userQueue = userQueue;
    }

    @Override
    public void addAgent(User user) {
        try {
            userQueue.put(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getAgent() {
        User result;
        try {
            result = userQueue.take();
        } catch (InterruptedException e) {
            result = null;
        }

        return result;
    }

    @Override
    public boolean hasAgent() {
        return !userQueue.isEmpty();
    }

    @Override
    public void removeAgent(User user) {
        userQueue.remove(user);
    }
}
