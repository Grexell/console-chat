package by.dima.model.logic.service.impl;

import by.dima.model.entity.*;
import by.dima.model.logic.MessageBuilder;
import by.dima.model.logic.repository.*;
import by.dima.model.logic.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryChatService implements ChatService {

    private UserRepository userRepository;
    private AgentRepository agentRepository;
    private MessageRepository messageRepository;
    private ChatRepository chatRepository;
    private UserQueueRepository userQueueRepository;

    @Autowired
    public InMemoryChatService(UserRepository userRepository, AgentRepository agentRepository, MessageRepository messageRepository, ChatRepository chatRepository, UserQueueRepository userQueueRepository) {
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userQueueRepository = userQueueRepository;
    }

    @Override
    public void register(User user) {
        if (!userRepository.isRegistered(user)) {
            userRepository.add(user);
            messageRepository.sendMessage(user, MessageBuilder.justRegistered());
        } else {
            messageRepository.sendMessage(user, MessageBuilder.alreadyRegistered());
        }
    }

    @Override
    public void delete(User user) {
        if (Role.CLIENT == user.getRole()) {
            userRepository.remove(user);
            userQueueRepository.remove(user);
        } else if (!chatRepository.isChated(user)) {
            agentRepository.removeAgent(user);
            userRepository.remove(user);
        } else {

        }
    }

    @Override
    public void engageChat(User user) {
        if (Role.AGENT == user.getRole()) {
            if (userQueueRepository.hasUser()) {
                User client = userQueueRepository.get();
                chatRepository.startChat(client, user);

                messageRepository.sendMessage(user, MessageBuilder.connectedToClient());
                messageRepository.sendMessage(client, MessageBuilder.connectedToAgent());
            } else {
                messageRepository.sendMessage(user, MessageBuilder.sendingToQueue());
                agentRepository.addAgent(user);
            }
        } else {
            if (agentRepository.hasAgent()) {
                messageRepository.sendMessage(user, MessageBuilder.lookingForAgent());

                User agent = agentRepository.getAgent();
                chatRepository.startChat(user, agent);
                messageRepository.sendMessage(user, MessageBuilder.connectedToAgent());
                messageRepository.sendMessage(agent, MessageBuilder.connectedToClient());
            } else {
                messageRepository.sendMessage(user, MessageBuilder.sendingToQueue());

                userQueueRepository.add(user);
            }
        }
    }

    @Override
    public void endChat(User user) {
        if (Role.CLIENT == user.getRole() && chatRepository.isChated(user)) {
            User agent = chatRepository.chatedAgent(user);
            chatRepository.endChat(user, agent);
            messageRepository.sendMessage(agent, MessageBuilder.clientExited());
        }
    }

    @Override
    public void sendMessage(User sender, Message message) {
        if (userRepository.isRegistered(sender) && chatRepository.isChated(sender)) {
            User distUser;

            if (Role.AGENT == sender.getRole()) {
                distUser = chatRepository.chatedUser(sender);
            } else {
                distUser = chatRepository.chatedAgent(sender);
            }
            messageRepository.sendMessage(distUser, message);
        }
    }

    @Override
    public List<Message> getNewMessages(User user) {
        List<Message> messages = new ArrayList<>();

        while(messageRepository.hasMessage(user)) {
            Message message = messageRepository.getMessage(user);
            if (message != null) {
                messages.add(message);
            }
        }

        return messages;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.getById(id);
    }
}
