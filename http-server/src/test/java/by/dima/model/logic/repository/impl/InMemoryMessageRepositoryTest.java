package by.dima.model.logic.repository.impl;

import by.dima.model.entity.Message;
import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import static org.junit.Assert.*;

public class InMemoryMessageRepositoryTest {

    private InMemoryMessageRepository messageRepository;
    private Map<User, BlockingQueue<Message>> messageMap;

    @Before
    public void setUp() {
        messageMap = new HashMap<>();
        messageRepository = new InMemoryMessageRepository(messageMap);
    }

    @Test
    public void sendMessage() {
        User user = new User("1", "1", Role.CLIENT);

        assertFalse(messageMap.containsKey(user));

        messageRepository.sendMessage(user, new Message("a", new Date(), user));

        assertTrue(messageMap.containsKey(user));
        assertEquals(messageMap.get(user).size(), 1);

        messageRepository.sendMessage(user, new Message("a", new Date(), user));
        assertEquals(messageMap.get(user).size(), 2);
    }

    @Test
    public void getMessage() {
        User user = new User("1", "1", Role.CLIENT);

        Message message = new Message("a", new Date(), user);
        messageRepository.sendMessage(user, message);
        messageRepository.sendMessage(user, new Message("a", new Date(), user));

        assertEquals(messageRepository.getMessage(user), message);
        assertEquals(messageMap.get(user).size(), 1);
    }

    @Test
    public void hasMessage() {
        User user = new User("1", "1", Role.CLIENT);
        assertFalse(messageRepository.hasMessage(user));
        messageRepository.sendMessage(user, new Message("a", new Date(), user));
        assertTrue(messageRepository.hasMessage(user));
    }
}