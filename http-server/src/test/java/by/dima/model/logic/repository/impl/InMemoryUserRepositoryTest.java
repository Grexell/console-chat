package by.dima.model.logic.repository.impl;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.*;

public class InMemoryUserRepositoryTest {

    private InMemoryUserRepository userRepository;
    private Map<String, User> userMap;
    private User user = new User("1", "1", Role.CLIENT);

    @Before
    public void setUp() {
        userMap = new HashMap<>();
        userRepository = new InMemoryUserRepository(userMap);
    }

    @Test
    public void add() {
        userRepository.add(user);
        assertTrue(userMap.containsKey(user.getId()));
        assertTrue(userMap.containsValue(user));
    }

    @Test
    public void remove() {
        userRepository.add(user);

        userRepository.remove(user);
        assertTrue(userMap.isEmpty());
    }

    @Test
    public void getById() {
        userRepository.add(user);

        assertEquals(userRepository.getById(user.getId()), user);
    }

    @Test
    public void isRegistered() {
        userRepository.add(user);

        assertTrue(userRepository.isRegistered(user));
        userRepository.remove(user);
        assertFalse(userRepository.isRegistered(user));
    }

    @Test
    public void getAllUsers() {
        userRepository.add(user);

        User u1 = new User("u1", "u1", Role.CLIENT);

        userRepository.add(u1);

        assertEquals(userRepository.getAllUsers(), new LinkedList<>(userMap.values()));
    }
}