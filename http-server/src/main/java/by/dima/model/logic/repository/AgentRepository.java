package by.dima.model.logic.repository;

import by.dima.model.entity.User;

import java.util.List;

public interface AgentRepository {
    void addAgent(User user);
    void removeAgent(User user);
    User getAgent();
    boolean hasAgent();

    List<User> getAll();
}
