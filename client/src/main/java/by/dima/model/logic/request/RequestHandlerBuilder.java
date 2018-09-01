package by.dima.model.logic.request;

import by.dima.model.logic.request.impl.*;
import by.dima.util.ConsoleUserOutput;
import by.dima.util.SimpleMessageFormatter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RequestHandlerBuilder {
    public static final String MESSAGE_COMMAND = "messages";
    public static final String SEND_MESSAGE_COMMAND = "message";
    public static final String EXIT_COMMAND = "exit";
    public static final String LEAVE_COMMAND = "leave";
    public static final String REGISTER_COMMAND = "register";

    public static final String MESSAGE_URL = "http://127.0.0.1:8080/api/{userToken}/messages";
    public static final String SEND_MESSAGE_URL = "http://127.0.0.1:8080/api/{userToken}/send";
    public static final String EXIT_URL = "http://127.0.0.1:8080/api/{userToken}/exit";
    public static final String LEAVE_URL = "http://127.0.0.1:8080/api/{userToken}/leave";
    public static final String REGISTER_URL = "http://127.0.0.1:8080/api/register";

    public static final String USER_PATTERN = "{userToken}";

    private static final Map<String, RequestHandler> REQUEST_HANDLER_MAP;

    static{
        REQUEST_HANDLER_MAP = new HashMap<>();

        REQUEST_HANDLER_MAP.put(MESSAGE_COMMAND, new MessageRequestHandler(
                new RestTemplate(),
                MESSAGE_URL,
                USER_PATTERN,
                new ConsoleUserOutput(),
                new SimpleMessageFormatter()));

        REQUEST_HANDLER_MAP.put(SEND_MESSAGE_COMMAND,
                new MessageSendingRequestHandler(new RestTemplate(), SEND_MESSAGE_URL, USER_PATTERN));
        REQUEST_HANDLER_MAP.put(REGISTER_COMMAND, new RegisterRequestHandler(new RestTemplate(), REGISTER_URL));
        REQUEST_HANDLER_MAP.put(EXIT_COMMAND, new ExitRequestHandler(new RestTemplate(), EXIT_URL, USER_PATTERN));
        REQUEST_HANDLER_MAP.put(LEAVE_COMMAND, new LeaveRequestHandler(new RestTemplate(), LEAVE_URL, USER_PATTERN));
    }


    public static RequestHandler build(String command){
        return REQUEST_HANDLER_MAP.get(command);
    }
}
