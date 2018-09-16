package by.dima.model.logic.repository.impl;

import by.dima.model.entity.Chat;
import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class InMemoryChatRepositoryTest {

    private InMemoryChatRepository chatRepository;
    private Map<User, Chat> chats;
    private Map<String, Chat> chatMap;

    @Before
    public void setUp() {
        chats = new HashMap<>();
        chatMap = new HashMap<>();
        chatRepository = new InMemoryChatRepository(chats, chatMap);
    }

    @Test
    public void startChat() {
        User agent = new User("1", "1", Role.AGENT);
        User client = new User("1", "1", Role.CLIENT);
        chatRepository.startChat(client, agent);
        assertTrue(chats.containsKey(client));
        assertTrue(chats.containsKey(agent));

        assertNotNull(chats.get(client));
        assertNotNull(chats.get(agent));
    }

    @Test
    public void endChat() {
        User agent = new User("1", "1", Role.AGENT);
        User client = new User("1", "1", Role.CLIENT);
        chatRepository.startChat(client, agent);

        chatRepository.endChat(client, agent);

        assertTrue(chats.isEmpty());
    }

    @Test
    public void chatedAgent() {
        User agent = new User("1", "1", Role.AGENT);
        User client = new User("1", "1", Role.CLIENT);
        chatRepository.startChat(client, agent);

        assertEquals(chatRepository.chatedAgent(client), agent);
    }

    @Test
    public void chatedClient() {
        User agent = new User("1", "1", Role.AGENT);
        User client = new User("1", "1", Role.CLIENT);
        chatRepository.startChat(client, agent);

        assertEquals(chatRepository.chatedClient(agent), client);
    }

    @Test
    public void isChated() {
        User agent = new User("1", "1", Role.AGENT);
        User client = new User("1", "1", Role.CLIENT);
        chatRepository.startChat(client, agent);

        assertTrue(chatRepository.isChated(agent));
        assertTrue(chatRepository.isChated(client));
    }

    @Test
    public void getChat() {
        User agent = new User("1", "1", Role.AGENT);
        User client = new User("1", "1", Role.CLIENT);
        chatRepository.startChat(client, agent);

        assertEquals(chatRepository.getChat(agent).getAgent(), agent);
        assertEquals(chatRepository.getChat(agent).getCustomer(), client);

        assertEquals(chatRepository.getChat(client).getAgent(), agent);
        assertEquals(chatRepository.getChat(client).getCustomer(), client);
    }

    @Test
    public void getById() {
        User agent = new User("1", "1", Role.AGENT);
        User client = new User("1", "1", Role.CLIENT);
        Chat chat = new Chat(client, agent);

        chatMap.put(chat.getId(), chat);

        assertEquals(chatRepository.getById(chat.getId()), chat);
    }

    @Test
    public void getAllOpenedChats() {
        User agent = new User("0", "1", Role.AGENT);
        User client = new User("0", "1", Role.CLIENT);

        User agent1 = new User("1", "1", Role.AGENT);
        User client1 = new User("1", "1", Role.CLIENT);

        User agent2 = new User("2", "1", Role.AGENT);
        User client2 = new User("2", "1", Role.CLIENT);

        User agent3 = new User("3", "1", Role.AGENT);
        User client3 = new User("3", "1", Role.CLIENT);

        User agent4 = new User("4", "1", Role.AGENT);
        User client4 = new User("4", "1", Role.CLIENT);

        chatRepository.startChat(client, agent);
        chatRepository.startChat(client1, agent1);
        chatRepository.startChat(client2, agent2);
        chatRepository.startChat(client3, agent3);
        chatRepository.startChat(client4, agent4);

        assertEquals(chatRepository.getAllOpenedChats().size(), 5);
    }
}