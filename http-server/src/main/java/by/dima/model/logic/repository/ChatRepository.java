package by.dima.model.logic.repository;

import by.dima.model.entity.User;

public interface ChatRepository {
    void startChat(User Client, User agent);
    void endChat(User client, User agent);
    User chatedAgent(User client);
    User chatedUser(User agent);
    boolean isChated(User user);
}
