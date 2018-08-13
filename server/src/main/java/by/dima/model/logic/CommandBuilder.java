package by.dima.model.logic;

import by.dima.model.entity.command.*;
import by.dima.model.logic.impl.InMemoryAgentService;
import by.dima.model.logic.impl.InMemoryChatService;
import by.dima.model.logic.impl.InMemoryUserService;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;

public class CommandBuilder {

    public static final String REGISTER_COMMAND = "register";
    public static final String EXIT_COMMAND = "exit";
    public static final String UNREGISTER_COMMAND = "leave";
    public static final String MESSAGE_COMMAND = "message";

    private static UserService userService = new InMemoryUserService();
    private static AgentService agentService = new InMemoryAgentService();
    private static ChatService chatService = new InMemoryChatService();
    private static ObjectConverter converter = new JasonObjectConverter();

    public static Command getCommand(String command){
        Command result;
        switch (command){
            case REGISTER_COMMAND:
                result = new RegisterCommand(converter, userService, agentService, chatService);
                break;
            case EXIT_COMMAND:
            case UNREGISTER_COMMAND:
                result = new UnregisterCommand(converter, userService, agentService, chatService);
                break;
            case MESSAGE_COMMAND:
                result = new ChatCommand(converter, userService, chatService);
                break;
            default:
                result = new LogCommand();
        }
        return result;
    }
}
