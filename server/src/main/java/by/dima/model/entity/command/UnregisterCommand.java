package by.dima.model.entity.command;

import by.dima.model.entity.*;
import by.dima.model.logic.*;
import by.dima.model.logic.impl.InMemoryAgentService;
import by.dima.model.logic.impl.InMemoryChatService;
import by.dima.model.logic.impl.InMemoryUserService;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;

import java.net.Socket;

public class UnregisterCommand implements Command {
    private ObjectConverter converter;
    private UserService userService;
    private AgentService agentService;
    private ChatService chatService;

    public UnregisterCommand() {
        this(new JasonObjectConverter(), new InMemoryUserService(), new InMemoryAgentService(), new InMemoryChatService());
    }

    public UnregisterCommand(ObjectConverter converter, UserService userService, AgentService agentService, ChatService chatService) {
        this.converter = converter;
        this.userService = userService;
        this.agentService = agentService;
        this.chatService = chatService;
    }

    @Override
    public void execute(Socket socket, String data) {
        User user = converter.read(data, User.class);
        user.setId(socket.getInetAddress() + user.getUsername());
        if (userService.isRegistered(user)) {
            MessageSender messageSender = userService.get(user);
            userService.remove(user);
            if (Role.CLIENT == user.getRole()) {
                userService.get(chatService.chatedAgent(user)).send(converter.write(RequestBuilder.clientExited()));
                agentService.addAgent(chatService.chatedAgent(user));
                chatService.endChat(user, chatService.chatedAgent(user));
            }
            if (Role.AGENT == user.getRole() && !chatService.isChated(user)) {
                agentService.removeAgent(user);
            }
        }
    }
}
