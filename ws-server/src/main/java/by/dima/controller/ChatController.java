package by.dima.controller;

import by.dima.model.entity.*;
import by.dima.model.logic.*;
import by.dima.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {

    private UserRepository userRepository;
    private AgentRepository agentRepository;
    private MessageService messageService;
    private ChatRepository chatRepository;
    private UserQueueService userQueueService;
    @Autowired
    private ObjectConverter converter;

    @Autowired
    public ChatController(UserRepository userRepository, AgentRepository agentRepository, MessageService messageService, ChatRepository chatRepository, UserQueueService userQueueService) {
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.messageService = messageService;
        this.chatRepository = chatRepository;
        this.userQueueService = userQueueService;
    }

    @RequestMapping("/register")
    public ResponseEntity<Request> register(@RequestBody User user, HttpServletRequest request){
        Request result;
        if (user != null) {
            user.setId(request.getRemoteAddr() + user.getUsername());

            if (!userRepository.isRegistered(user)){
                userRepository.add(user);
                messageService.sendMessage(user, MessageBuilder.justRegistered());
                if (Role.AGENT == user.getRole()) {
                    if (userQueueService.hasUser()) {
                        chatRepository.startChat(userQueueService.get(), user);

                        messageService.sendMessage(user, MessageBuilder.connected());
                    } else {
                        agentRepository.addAgent(user);
                    }
                } else {
                    if (agentRepository.hasAgent()) {
                        messageService.sendMessage(user, MessageBuilder.lookingForAgent());

                        chatRepository.startChat(user, agentRepository.getAgent());
                    } else {
                        messageService.sendMessage(user, MessageBuilder.sendingToQueue());

                        userQueueService.add(user);
                    }
                }
            } else {
                messageService.sendMessage(user, MessageBuilder.alreadyRegistered());
            }

            result = new Request("registered", user.getId());
        } else {
            result = new Request();
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping("/{userToken}/exit")
    public ResponseEntity<Request> exit(@PathVariable String userToken){
        User user = userRepository.getById(userToken);
        Request result;
        if (user != null) {
            if (Role.CLIENT == user.getRole()) {
                chatRepository.endChat(user, chatRepository.chatedAgent(user));
                userRepository.remove(user);
                result = new Request("message", converter.write(new Message("Exited", new Date(), SystemUserHolder.SYSTEM_USER)));
            } else if (!chatRepository.isChated(user)){
                agentRepository.removeAgent(user);
                result = new Request("message", converter.write(new Message("Exited", new Date(), SystemUserHolder.SYSTEM_USER)));
            } else {
                result = new Request("message", converter.write(new Message("End all chats before", new Date(), SystemUserHolder.SYSTEM_USER)));
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/{userToken}/leave")
    public ResponseEntity<Request> leave(@PathVariable String userToken){
        User user = userRepository.getById(userToken);
        Request result;
        if (user != null) {
            if (Role.CLIENT == user.getRole()) {
                chatRepository.endChat(user, chatRepository.chatedAgent(user));
                result = new Request("message", converter.write(new Message("Exited", new Date(), SystemUserHolder.SYSTEM_USER)));

                if (agentRepository.hasAgent()) {
                    messageService.sendMessage(user, MessageBuilder.lookingForAgent());

                    chatRepository.startChat(user, agentRepository.getAgent());
                } else {
                    messageService.sendMessage(user, MessageBuilder.sendingToQueue());

                    userQueueService.add(user);
                }
            } else {
                result = new Request("message", converter.write(new Message("End all chats before", new Date(), SystemUserHolder.SYSTEM_USER)));
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/{userToken}/send")
    public ResponseEntity<Request> sendMessage(@PathVariable String userToken, @RequestBody Message message){
        User user = userRepository.getById(userToken);
        if (userRepository.isRegistered(user)) {
            User distUser;

            if (Role.AGENT == user.getRole()) {
                distUser = chatRepository.chatedUser(user);
            } else {
                distUser = chatRepository.chatedAgent(user);
            }
            messageService.sendMessage(distUser, message);
        }
        return null;
    }

    @RequestMapping("/{userToken}/messages")
    public ResponseEntity getNewMessages(@PathVariable String userToken){
        List<Message> messages = new ArrayList<>();
        User user = userRepository.getById(userToken);

        while(messageService.hasMessage(user)) {
            messages.add(messageService.getMessage(user));
        }

        return new ResponseEntity(messages, HttpStatus.OK);
    }
}
