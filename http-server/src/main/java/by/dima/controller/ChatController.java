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
    private MessageRepository messageRepository;
    private ChatRepository chatRepository;
    private UserQueueService userQueueService;
    @Autowired
    private ObjectConverter converter;

    @Autowired
    public ChatController(UserRepository userRepository, AgentRepository agentRepository, MessageRepository messageRepository, ChatRepository chatRepository, UserQueueService userQueueService) {
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userQueueService = userQueueService;
    }

    @RequestMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user, HttpServletRequest request){
        Response result;
        if (user != null) {
            user.setId("" + request.getRemoteAddr().hashCode() + user.getUsername().hashCode());

            if (!userRepository.isRegistered(user)){
                userRepository.add(user);
                messageRepository.sendMessage(user, MessageBuilder.justRegistered());
                if (Role.AGENT == user.getRole()) {
                    if (userQueueService.hasUser()) {
                        chatRepository.startChat(userQueueService.get(), user);

                        messageRepository.sendMessage(user, MessageBuilder.connected());
                    } else {
                        agentRepository.addAgent(user);
                    }
                } else {
                    if (agentRepository.hasAgent()) {
                        messageRepository.sendMessage(user, MessageBuilder.lookingForAgent());

                        chatRepository.startChat(user, agentRepository.getAgent());
                    } else {
                        messageRepository.sendMessage(user, MessageBuilder.sendingToQueue());

                        userQueueService.add(user);
                    }
                }
            } else {
                messageRepository.sendMessage(user, MessageBuilder.alreadyRegistered());
            }

            result = new Response("registered", user.getId());
        } else {
            result = new Response();
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping("/{userToken}/exit")
    public ResponseEntity<Response> exit(@PathVariable String userToken){
        User user = userRepository.getById(userToken);
        Response result;
        if (user != null) {
            if (Role.CLIENT == user.getRole()) {
                if (chatRepository.isChated(user)) {
                    chatRepository.endChat(user, chatRepository.chatedAgent(user));
                }
                userRepository.remove(user);
                result = new Response("message", converter.write(new Message("Exited", new Date(), SystemUserHolder.SYSTEM_USER)));
            } else if (!chatRepository.isChated(user)){
                agentRepository.removeAgent(user);
                result = new Response("message", converter.write(new Message("Exited", new Date(), SystemUserHolder.SYSTEM_USER)));
            } else {
                result = new Response("message", converter.write(new Message("End all chats before", new Date(), SystemUserHolder.SYSTEM_USER)));
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/{userToken}/leave")
    public ResponseEntity<Response> leave(@PathVariable String userToken){
        User user = userRepository.getById(userToken);
        Response result;
        if (user != null) {
            if (Role.CLIENT == user.getRole()) {
                chatRepository.endChat(user, chatRepository.chatedAgent(user));
                result = new Response("message", converter.write(new Message("Exited", new Date(), SystemUserHolder.SYSTEM_USER)));

                if (agentRepository.hasAgent()) {
                    messageRepository.sendMessage(user, MessageBuilder.lookingForAgent());

                    chatRepository.startChat(user, agentRepository.getAgent());
                } else {
                    messageRepository.sendMessage(user, MessageBuilder.sendingToQueue());

                    userQueueService.add(user);
                }
            } else {
                result = new Response("message", converter.write(new Message("End all chats before", new Date(), SystemUserHolder.SYSTEM_USER)));
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/{userToken}/send")
    public ResponseEntity<Response> sendMessage(@PathVariable String userToken, @RequestBody Message message){
        User user = userRepository.getById(userToken);
        if (userRepository.isRegistered(user)) {
            User distUser;

            if (Role.AGENT == user.getRole()) {
                distUser = chatRepository.chatedUser(user);
            } else {
                distUser = chatRepository.chatedAgent(user);
            }
            messageRepository.sendMessage(distUser, message);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/{userToken}/messages")
    public ResponseEntity getNewMessages(@PathVariable String userToken){
        List<Message> messages = new ArrayList<>();
        User user = userRepository.getById(userToken);

        while(messageRepository.hasMessage(user)) {
            messages.add(messageRepository.getMessage(user));
        }

        return new ResponseEntity(messages, HttpStatus.OK);
    }
}