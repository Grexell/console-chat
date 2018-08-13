package by.dima.model.logic;

import by.dima.model.entity.User;

public interface AgentService {
    void addAgent(User user);
    void removeAgent(User user);
    User getAgent();
    boolean hasAgent();
}
