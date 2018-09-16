package by.dima.model.logic.repository;

import by.dima.model.entity.Chat;
import by.dima.model.entity.User;

import java.util.List;

public interface ChatRepository {
    void startChat(User Client, User agent);
    void endChat(User client, User agent);
    User chatedAgent(User client);

    User chatedClient(User agent);
    boolean isChated(User user);

    Chat getChat(User user);

    Chat getById(String id);

    List<String> getAllOpenedChats();
}
