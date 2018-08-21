package by.dima.model.logic.impl;

import by.dima.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

public class InMemoryAgentServiceTest {

    public BlockingQueue<User> queue;

    @Before
    public void setUp(){
        queue = new ArrayBlockingQueue<>(12);
    }

    @Test
    public void addAgent() {
        InMemoryAgentService service = new InMemoryAgentService(queue);

        service.addAgent(new User());

        assertEquals(queue.size(), 1);
    }

    @Test
    public void getAgent() {
        InMemoryAgentService service = new InMemoryAgentService(queue);

        User user = new User();

        service.addAgent(user);

        assertEquals(service.getAgent(), user);
    }

    @Test
    public void hasAgent() {
        InMemoryAgentService service = new InMemoryAgentService(queue);

        User user = new User();

        service.addAgent(user);

        assertTrue(service.hasAgent());
    }

    @Test
    public void removeAgent() {
        InMemoryAgentService service = new InMemoryAgentService(queue);

        User user1 = new User();
        User user2 = new User();

        service.addAgent(user1);
        service.addAgent(user2);

        service.removeAgent(user1);

        assertEquals(queue.size(), 1);
        assertEquals(queue.peek(), user2);
    }
}