package by.dima.model.logic.repository.impl;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class InMemoryUserQueueRepositoryTest {

    private InMemoryUserQueueRepository userQueue;
    private BlockingQueue<User> users;
    private User user = new User("1", "1", Role.CLIENT);

    @Before
    public void setUp() {
        users = new LinkedBlockingQueue<>();
        userQueue = new InMemoryUserQueueRepository(users);
    }

    @Test
    public void add() {
        int expected = users.size() + 1;
        userQueue.add(user);
        assertEquals(users.size(), expected);
    }

    @Test
    public void remove() {
        userQueue.add(user);
        int expected = users.size() - 1;
        userQueue.remove(user);
        assertEquals(users.size(), expected);
    }

    @Test
    public void get() {
        userQueue.add(user);
        User u1 = new User("u1", "1", Role.CLIENT);
        userQueue.add(u1);

        assertEquals(userQueue.get(), user);
        assertFalse(users.contains(user));
        assertEquals(users.size(), 1);
    }

    @Test
    public void hasUser() {
        assertFalse(userQueue.hasUser());
        userQueue.add(user);
        assertTrue(userQueue.hasUser());
    }

    @Test
    public void getAllUsers() {
        userQueue.add(user);
        User u1 = new User("u1", "1", Role.CLIENT);

        assertEquals(userQueue.getAllUsers(), new LinkedList<>(users));
    }
}