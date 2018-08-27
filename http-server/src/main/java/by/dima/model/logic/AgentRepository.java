package by.dima.model.logic;

import by.dima.model.entity.User;

public interface AgentRepository {
    void addAgent(User user);
    void removeAgent(User user);
    User getAgent();
    boolean hasAgent();
}
