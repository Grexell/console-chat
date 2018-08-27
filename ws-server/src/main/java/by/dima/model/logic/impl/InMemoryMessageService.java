package by.dima.model.logic.impl;

import by.dima.model.entity.Message;
import by.dima.model.entity.User;
import by.dima.model.logic.MessageService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class InMemoryMessageService implements MessageService {
    private Map<User, BlockingQueue<Message>> messages;

    public InMemoryMessageService() {
        messages = new HashMap<>();
    }

    public InMemoryMessageService(Map<User, BlockingQueue<Message>> messages) {
        this.messages = messages;
    }

    @Override
    public void sendMessage(User user, Message message) {
        if (!messages.containsKey(user)) {
            messages.put(user, new LinkedBlockingQueue());
        }

        messages.get(user).add(message);
    }

    @Override
    public Message getMessage(User user) {
        try {
            return messages.get(user).take();
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public boolean hasMessage(User user) {
        return !messages.get(user).isEmpty();
    }
}
