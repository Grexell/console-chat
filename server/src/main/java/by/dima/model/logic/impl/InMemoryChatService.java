package by.dima.model.logic.impl;

import by.dima.model.entity.User;
import by.dima.model.logic.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class InMemoryChatService implements ChatService {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryChatService.class);
    private static final String CHAT_START_MESSAGE = "Chat started";
    private static final String CHAT_END_MESSAGE = "Chat ended";

    private Map<User, User> chats;

    public InMemoryChatService() {
        chats = new HashMap<>();
    }

    @Override
    public void startChat(User client, User agent) {
        chats.put(client, agent);
        LOG.info(CHAT_START_MESSAGE);
    }

    @Override
    public void endChat(User client, User agent) {
        chats.remove(client);
        LOG.info(CHAT_END_MESSAGE);
    }

    @Override
    public User chatedAgent(User client) {
        return chats.get(client);
    }

    @Override
    public User chatedUser(User agent) {
        User client = null;
        for (Map.Entry<User, User> chat: chats.entrySet()) {
            if (chat.getValue().equals(agent)){
                client = chat.getKey();
                break;
            }
        }
        return client;
    }

    @Override
    public boolean isChated(User user) {
        return chats.containsKey(user) || chats.containsValue(user);
    }
}
