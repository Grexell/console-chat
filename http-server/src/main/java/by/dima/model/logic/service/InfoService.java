package by.dima.model.logic.service;

import by.dima.model.entity.User;

import java.util.List;

public interface InfoService {
    List<String> getAllAgentUsernames();
    List<String> getFreeAgentUsernames();
    User getAgentInfo(String username);
    List<String> getOppenedChatID();
}
