package by.dima.model.logic.repository.impl;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class InMemoryAgentRepositoryTest {

    private InMemoryAgentRepository agentRepository;
    private BlockingQueue<User> users;

    @Before
    public void setUp() {
        users = new LinkedBlockingQueue<>();
        agentRepository = new InMemoryAgentRepository(users);
    }

    @Test
    public void addAgent() {
        int before = users.size();
        agentRepository.addAgent(new User("test user", Role.AGENT));
        assertEquals(users.size(), before + 1);
    }

    @Test
    public void getAgent() {
        User agent = new User("test user", Role.AGENT);
        agentRepository.addAgent(agent);

        assertEquals(agentRepository.getAgent(), agent);
    }

    @Test
    public void hasAgent() {
        agentRepository.addAgent(new User("test user", Role.AGENT));

        assertTrue(agentRepository.hasAgent());
        agentRepository.getAgent();
        assertFalse(agentRepository.hasAgent());
    }

    @Test
    public void getAll() {
        agentRepository.addAgent(new User("test user1", Role.AGENT));
        agentRepository.addAgent(new User("test user2", Role.AGENT));
        agentRepository.addAgent(new User("test user3", Role.AGENT));

        assertEquals(agentRepository.getAll(), new LinkedList<>(users));
    }

    @Test
    public void removeAgent() {
        User agent = new User("1", "test user1", Role.AGENT);
        agentRepository.addAgent(agent);
        agentRepository.addAgent(new User("2", "test user2", Role.AGENT));
        agentRepository.addAgent(new User("3", "test user3", Role.AGENT));
        int result = users.size() - 1;

        agentRepository.removeAgent(agent);

        assertEquals(users.size(), result);
        assertFalse(users.contains(agent));
    }
}