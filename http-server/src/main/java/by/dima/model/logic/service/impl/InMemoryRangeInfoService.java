package by.dima.model.logic.service.impl;

import by.dima.model.entity.Chat;
import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.model.logic.repository.*;
import by.dima.model.logic.service.RangeInfoService;
import by.dima.util.ListPaginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InMemoryRangeInfoService implements RangeInfoService {
    private UserRepository userRepository;
    private AgentRepository agentRepository;
    private MessageRepository messageRepository;
    private ChatRepository chatRepository;
    private UserQueueRepository userQueueRepository;

    @Autowired
    public InMemoryRangeInfoService(UserRepository userRepository, AgentRepository agentRepository, MessageRepository messageRepository, ChatRepository chatRepository, UserQueueRepository userQueueRepository) {
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userQueueRepository = userQueueRepository;
    }

    @Override
    public List<String> getAllAgentIDs(int from, int length) {
        return ListPaginator.paginate(getAllAgentIDs(), from, from + length);
    }

    @Override
    public List<String> getFreeAgentIDs(int from, int length) {
        return ListPaginator.paginate(getFreeAgentIDs(), from, from + length);
    }

    @Override
    public List<String> getOpenedChatIDs(int from, int length) {
        return ListPaginator.paginate(getOppenedChatID(), from, from + length);
    }

    @Override
    public List<String> getQueuedUserIDs(int from, int length) {
        return ListPaginator.paginate(getQueuedUsers(), from, from + length);
    }

    @Override
    public List<String> getAllAgentIDs() {
        return userRepository.getAllUsers().parallelStream().filter(user -> user.getRole() == Role.AGENT).map(user -> user.getId()).collect(Collectors.toList());
    }

    @Override
    public List<String> getFreeAgentIDs() {
        return agentRepository.getAll().parallelStream().map(user -> user.getId()).collect(Collectors.toList());
    }

    @Override
    public User getAgentInfo(String username) {
        return userRepository.getAllUsers().parallelStream().filter(user -> user.getRole() == Role.AGENT && user.getUsername().equalsIgnoreCase(username)).findFirst().get();
    }

    @Override
    public List<String> getOppenedChatID() {
        return chatRepository.getAllOpenedChats();
    }

    @Override
    public Chat getChatDetails(String id) {
        return chatRepository.getById(id);
    }

    @Override
    public List<String> getQueuedUsers() {
        return userQueueRepository.getAllUsers().parallelStream().map((user -> user.getId())).collect(Collectors.toList());
    }

    @Override
    public User getUserInfo(String id) {
        return userRepository.getById(id);
    }
}
