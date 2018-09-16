package by.dima.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {
    @RequestMapping
    public ResponseEntity getFreeAgents(){
        return null;
    }

    @RequestMapping
    public ResponseEntity getAllAgents(){
        return null;
    }

    @RequestMapping
    public ResponseEntity getAgentInfo(){
        return null;
    }

    @RequestMapping
    public ResponseEntity getFreeAgentAmount(){
        return null;
    }

    @RequestMapping
    public ResponseEntity getChats(){
        return null;
    }

    @RequestMapping
    public ResponseEntity getUser(){}
}
