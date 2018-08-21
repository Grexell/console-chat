package by.dima.model.logic.impl;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.model.logic.MessageSender;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InMemoryUserServiceTest {

    Map<User, MessageSender> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
    }

    @Test
    public void add() {
        InMemoryUserService service = new InMemoryUserService(map);

        User user = new User("id1", "u1", Role.CLIENT);
        MessageSender sender = message -> {};
        service.add(user, sender);

        assertEquals(map.get(user), sender);
    }

    @Test
    public void get() {
        InMemoryUserService service = new InMemoryUserService(map);

        User user1 = new User("id1", "u1", Role.CLIENT);
        MessageSender sender1 = message -> {};

        User user2 = new User("id2", "u2", Role.CLIENT);
        MessageSender sender2 = message -> {};

        service.add(user1, sender1);
        service.add(user2, sender2);

        assertEquals(service.get(user2), sender2);
    }

    @Test
    public void remove() {
        InMemoryUserService service = new InMemoryUserService(map);

        User user1 = new User("id1", "u1", Role.CLIENT);
        MessageSender sender1 = message -> {};

        User user2 = new User("id2", "u2", Role.CLIENT);
        MessageSender sender2 = message -> {};

        service.add(user1, sender1);
        service.add(user2, sender2);

        service.remove(user1);

        assertFalse(map.containsKey(user1));
        assertFalse(map.containsValue(sender1));
    }

    @Test
    public void isRegistered() {
        InMemoryUserService service = new InMemoryUserService(map);

        User user = new User("id1", "u1", Role.CLIENT);
        MessageSender sender = message -> {};
        service.add(user, sender);

        assertTrue(service.isRegistered(user));
    }
}