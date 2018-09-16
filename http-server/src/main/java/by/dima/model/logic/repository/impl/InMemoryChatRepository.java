package by.dima.model.logic.repository.impl;

import by.dima.model.entity.Chat;
import by.dima.model.entity.User;
import by.dima.model.logic.repository.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryChatRepository implements ChatRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryChatRepository.class);
    private static final String CHAT_START_MESSAGE = "Chat started";
    private static final String CHAT_END_MESSAGE = "Chat ended";

    private Map<User, Chat> clientChats;
    private Map<String, Chat> chats;

    public InMemoryChatRepository() {
        this(new HashMap<>(), new HashMap<>());
    }

    public InMemoryChatRepository(Map<User, Chat> clientChats) {
        this(clientChats, new HashMap<>());
    }

    public InMemoryChatRepository(Map<User, Chat> clientChats, Map<String, Chat> chats) {
        this.clientChats = clientChats;
        this.chats = chats;
    }

    @Override
    public void startChat(User client, User agent) {
        Chat chat = new Chat(client, agent);
        clientChats.put(client, chat);
        clientChats.put(agent, chat);
        chats.put(chat.getId(), chat);
        LOG.info(CHAT_START_MESSAGE);
    }

    @Override
    public void endChat(User client, User agent) {
        Chat deleteChat = clientChats.remove(client);
        clientChats.remove(agent);
        chats.remove(deleteChat.getId());
        LOG.info(CHAT_END_MESSAGE);
    }

    @Override
    public User chatedAgent(User client) {
        return clientChats.get(client).getAgent();
    }

    @Override
    public User chatedClient(User agent) {
        return clientChats.get(agent).getCustomer();
    }

    @Override
    public boolean isChated(User user) {
        return clientChats.containsKey(user);
    }

    @Override
    public Chat getChat(User user) {
        return clientChats.get(user);
    }

    @Override
    public Chat getById(String id) {
        return chats.get(id);
    }

    @Override
    public List<String> getAllOpenedChats() {
        return new LinkedList<>(chats.keySet());
    }
}
