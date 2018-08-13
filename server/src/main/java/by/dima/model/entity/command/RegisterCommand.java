package by.dima.model.entity.command;

import by.dima.model.entity.*;
import by.dima.model.logic.*;
import by.dima.model.logic.impl.InMemoryAgentService;
import by.dima.model.logic.impl.InMemoryChatService;
import by.dima.model.logic.impl.InMemoryUserService;
import by.dima.model.logic.impl.StreamMessageSender;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;

import java.io.IOException;
import java.net.Socket;

public class RegisterCommand implements Command {
    private ObjectConverter converter;
    private UserService userService;
    private AgentService agentService;
    private ChatService chatService;

    public RegisterCommand() {
        this(new JasonObjectConverter(), new InMemoryUserService(), new InMemoryAgentService(), new InMemoryChatService());
    }

    public RegisterCommand(ObjectConverter converter, UserService userService, AgentService agentService, ChatService chatService) {
        this.converter = converter;
        this.userService = userService;
        this.agentService = agentService;
        this.chatService = chatService;
    }

    @Override
    public void execute(Socket socket, String data) {
        User user = converter.read(data, User.class);
        user.setId(socket.getInetAddress() + user.getUsername());

        MessageSender messageSender;
        try {
            messageSender = new StreamMessageSender(socket.getOutputStream());
        } catch (IOException e) {
            messageSender = null;
        }

        if (userService.isRegistered(user)) {
            messageSender.send(converter.write(RequestBuilder.alreadyRegistered()));
        } else {
            userService.add(user, messageSender);
            messageSender.send(converter.write(RequestBuilder.justRegistered()));
            if (Role.AGENT.equals(user.getRole())) {
                agentService.addAgent(user);
            } else {
                if (!agentService.hasAgent()) {
                    messageSender.send(converter.write(RequestBuilder.lookingForAgent()));
                }
                User agent = agentService.getAgent();
                messageSender.send(converter.write(RequestBuilder.connected()));
                chatService.startChat(user, agent);
            }
        }
    }
}
