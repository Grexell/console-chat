package by.dima.model.logic;

import by.dima.model.entity.Message;
import by.dima.model.entity.Request;
import by.dima.model.entity.SystemUserHolder;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;

import java.util.Date;

public class RequestBuilder {
    private static ObjectConverter converter = new JasonObjectConverter();

    public static final String MESSAGE_KEY = "message";

    public static final String ALREADY_REGISTERED = "Already registered";
    public static final String JUST_REGISTERED = "Registered";
    public static final String LOOKING_FOR_AGENT = "Looking for agent";
    public static final String CONNECTED = "Connected to agent";
    public static final String CLIENT_LEFT = "Client left";

    private static Request buildRequest(String command, String data){
        return new Request(command, converter.write(new Message(data, new Date(), SystemUserHolder.SYSTEM_USER)));
    }

    public static Request alreadyRegistered(){
        return buildRequest(MESSAGE_KEY, ALREADY_REGISTERED);
    }

    public static Request justRegistered(){
        return buildRequest(MESSAGE_KEY, JUST_REGISTERED);
    }

    public static Request lookingForAgent(){
        return buildRequest(MESSAGE_KEY, LOOKING_FOR_AGENT);
    }

    public static Request connected(){
        return buildRequest(MESSAGE_KEY, CONNECTED);
    }

    public static Request clientExited(){
        return buildRequest(MESSAGE_KEY, CLIENT_LEFT);
    }
}
