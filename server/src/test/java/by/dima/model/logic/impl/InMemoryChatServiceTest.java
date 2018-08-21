package by.dima.model.logic.impl;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.model.logic.MessageSender;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InMemoryChatServiceTest {

    Map<User, User> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
    }

    @Test
    public void startChat() {
        InMemoryChatService service = new InMemoryChatService(map);

        User user1 = new User("id1", "us1", Role.CLIENT);
        User user2 = new User("id2", "us2", Role.CLIENT);

        service.startChat(user1, user2);

        assertEquals(map.get(user1), user2);
    }

    @Test
    public void endChat() {
        InMemoryChatService service = new InMemoryChatService(map);

        User user1 = new User("id1", "us1", Role.CLIENT);
        User user2 = new User("id2", "us2", Role.CLIENT);

        service.startChat(user1, user2);

        service.endChat(user1,user2);

        assertFalse(map.containsKey(user1));
        assertFalse(map.containsValue(user2));
    }

    @Test
    public void chatedAgent() {
        InMemoryChatService service = new InMemoryChatService(map);

        User user1 = new User("id1", "us1", Role.CLIENT);
        User user2 = new User("id2", "us2", Role.CLIENT);

        service.startChat(user1, user2);

        assertEquals(service.chatedAgent(user1), user2);
    }

    @Test
    public void chatedUser() {
        InMemoryChatService service = new InMemoryChatService(map);

        User user1 = new User("id1", "us1", Role.CLIENT);
        User user2 = new User("id2", "us2", Role.CLIENT);

        service.startChat(user1, user2);

        assertEquals(service.chatedUser(user2), user1);
    }

    @Test
    public void isChated() {InMemoryChatService service = new InMemoryChatService(map);

        User user1 = new User("id1", "us1", Role.CLIENT);
        User user2 = new User("id2", "us2", Role.CLIENT);

        service.startChat(user1, user2);

        assertTrue(service.isChated(user1));
        assertTrue(service.isChated(user2));
    }
}