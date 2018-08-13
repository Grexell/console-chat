package by.dima.model.entity.command;

import by.dima.model.entity.Message;
import by.dima.model.entity.Request;
import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.model.logic.ChatService;
import by.dima.model.logic.UserService;
import by.dima.util.ObjectConverter;

import java.net.Socket;

public class ChatCommand implements Command{
    private ObjectConverter converter;
    private UserService userService;
    private ChatService chatService;

    public ChatCommand(ObjectConverter converter, UserService userService, ChatService chatService) {
        this.converter = converter;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public void execute(Socket socket, String data) {
        Message message = converter.read(data, Message.class);
        User user = message.getUser();
        user.setId(socket.getInetAddress() + user.getUsername());
        if (userService.isRegistered(user)) {
            User distUser;
            if (Role.AGENT == user.getRole()) {
                distUser = chatService.chatedUser(user);
            } else {
                distUser = chatService.chatedAgent(user);
            }
            userService.get(distUser).send(converter.write(new Request("message",data)));
        }
    }
}
