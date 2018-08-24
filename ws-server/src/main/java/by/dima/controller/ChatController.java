package by.dima.controller;

import by.dima.model.entity.Request;
import by.dima.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    @RequestMapping("/register")
    public ResponseEntity<Request> register(@RequestBody User user){

        return null;
    }

    @RequestMapping("/exit")
    public ResponseEntity<Request> exit(){
        return null;
    }

    @RequestMapping("/exit")
    public ResponseEntity<Request> leave(){
        return null;
    }

    @RequestMapping("/send")
    public ResponseEntity<Request> sendMessage(){
        return null;
    }

    @RequestMapping("/messages")
    public ResponseEntity<Request> getNewMessages(){
        return null;
    }
}
