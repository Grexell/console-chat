package by.dima.model.logic.service;

import by.dima.model.entity.Message;
import by.dima.model.entity.User;

import java.util.List;

public interface ChatService {
    void register(User user);
    void delete(User user);
    void engageChat(User user);
    void endChat(User user);
    void sendMessage(User sender, Message message);
    List<Message> getNewMessages(User user);
    User getUserById(String id);
}
