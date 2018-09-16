package by.dima.model.logic.service;

import by.dima.model.entity.Chat;
import by.dima.model.entity.User;

import java.util.List;

public interface InfoService {
    List<String> getAllAgentIDs();

    List<String> getFreeAgentIDs();
    User getAgentInfo(String username);
    List<String> getOppenedChatID();

    Chat getChatDetails(String id);

    List<String> getQueuedUsers();

    User getUserInfo(String id);
}
