package by.dima.model.logic.impl;

import by.dima.model.entity.User;
import by.dima.model.logic.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryChatRepository implements ChatRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryChatRepository.class);
    private static final String CHAT_START_MESSAGE = "Chat started";
    private static final String CHAT_END_MESSAGE = "Chat ended";

    private Map<User, User> chats;

    public InMemoryChatRepository() {
        chats = new HashMap<>();
    }

    public InMemoryChatRepository(Map<User, User> chats) {
        this.chats = chats;
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
