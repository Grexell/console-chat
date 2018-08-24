package by.dima.model.logic.impl;

import by.dima.model.entity.User;
import by.dima.model.logic.AgentRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Repository
public class InMemoryAgentRepository implements AgentRepository {
    private BlockingQueue<User> userQueue;

    public InMemoryAgentRepository() {
        userQueue = new LinkedBlockingQueue<>();
    }

    public InMemoryAgentRepository(BlockingQueue<User> userQueue) {
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
