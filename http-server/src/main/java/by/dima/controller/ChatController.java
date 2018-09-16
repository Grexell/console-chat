package by.dima.controller;

import by.dima.model.entity.Message;
import by.dima.model.entity.Response;
import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.model.logic.service.ChatService;
import by.dima.model.logic.service.SecurityService;
import by.dima.util.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ChatController {

    private ChatService chatService;

    @Autowired
    private ObjectConverter converter;

    @Autowired
    private SecurityService securityService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping("/register")
    public ResponseEntity register(@RequestBody User user, HttpServletRequest request) {
        String result;
        if (user != null) {
            user.setId("" + request.getRemoteAddr().hashCode() + user.getUsername().hashCode());

            chatService.register(user);
            chatService.engageChat(user);

            result = securityService.protect(user.getId());
        } else {
            result = new String();
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping("/{userToken}/exit")
    public ResponseEntity<Response> exit(@PathVariable String userToken) {
        String id = securityService.check(userToken);
        User user = chatService.getUserById(id);
        if (user != null) {

            chatService.endChat(user);
            chatService.delete(user);
            securityService.unprotect(userToken);

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/{userToken}/leave")
    public ResponseEntity<Response> leave(@PathVariable String userToken) {
        String id = securityService.check(userToken);
        User user = chatService.getUserById(id);
        if (user != null && user.getRole() == Role.CLIENT) {

            chatService.endChat(user);
            chatService.engageChat(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/{userToken}/send")
    public ResponseEntity sendMessage(@PathVariable String userToken, @RequestBody Message message) {
        String id = securityService.check(userToken);
        User user = chatService.getUserById(id);

        if (user != null) {

            chatService.sendMessage(user, message);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/{userToken}/send/{userId}")
    public ResponseEntity sendMessageTo(@PathVariable String userToken, @PathVariable String userId, @RequestBody Message message) {
        String id = securityService.check(userToken);
        User user = chatService.getUserById(id);

        if (user != null) {
            message.setUser(user);
            User dist = chatService.getUserById(userId);
            chatService.sendMessageTo(dist, message);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/{userToken}/messages")
    public ResponseEntity getNewMessages(@PathVariable String userToken) {
        String id = securityService.check(userToken);
        User user = chatService.getUserById(id);

        return new ResponseEntity(chatService.getNewMessages(user), HttpStatus.OK);
    }
}