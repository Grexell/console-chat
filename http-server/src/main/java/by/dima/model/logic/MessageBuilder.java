package by.dima.model.logic;

import by.dima.model.entity.Message;
import by.dima.model.entity.SystemUserHolder;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;

import java.util.Date;

public class MessageBuilder {
    private static ObjectConverter converter = new JasonObjectConverter();

//    public static final String MESSAGE_KEY = "message";

    public static final String ALREADY_REGISTERED = "Already registered";
    public static final String JUST_REGISTERED = "Registered";
    public static final String LOOKING_FOR_AGENT = "Looking for agent";
    public static final String CONNECTED_TO_CLIENT = "Connected to client";
    public static final String CONNECTED_TO_AGENT = "Connected to agent";
    public static final String CLIENT_LEFT = "Client left";
    public static final String SENDING_TO_QUEUE = "You are in queue";

    private static Message buildRequest(String data){
        return new Message(data, new Date(), SystemUserHolder.SYSTEM_USER);
    }

    public static Message alreadyRegistered(){
        return buildRequest(ALREADY_REGISTERED);
    }

    public static Message justRegistered(){
        return buildRequest(JUST_REGISTERED);
    }

    public static Message lookingForAgent(){
        return buildRequest(LOOKING_FOR_AGENT);
    }

    public static Message connectedToAgent(){
        return buildRequest(CONNECTED_TO_AGENT);
    }

    public static Message connectedToClient(){
        return buildRequest(CONNECTED_TO_CLIENT);
    }

    public static Message clientExited(){
        return buildRequest(CLIENT_LEFT);
    }

    public static Message sendingToQueue() {return buildRequest(SENDING_TO_QUEUE); }
}
