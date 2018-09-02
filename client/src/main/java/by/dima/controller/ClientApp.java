package by.dima.controller;

import by.dima.model.logic.*;
import by.dima.model.logic.preprocessor.DataPreprocessorBuilder;
import by.dima.model.logic.preprocessor.impl.CommandDataPreprocessor;
import by.dima.model.logic.request.RequestHandlerBuilder;
import by.dima.model.logic.preprocessor.DataPreprocessor;
import by.dima.model.logic.preprocessor.impl.MarkerDataPreprocessor;
import by.dima.util.UserInput;

public class ClientApp {
    public static void main(String[] args) {
        new ClientApp().run();
    }

    public static final String EXIT_COMMAND = "/exit";
    public static final String DATA_DELIMITER = " ";

    public ClientApp() {
        input = UserInput.console();
        receiver = new ResponseReceiver();

        commandParser = new CommandDataPreprocessor();
        dataPreprocessor = new MarkerDataPreprocessor(DATA_DELIMITER, null, true);

        new Thread(receiver).start();
    }

    private UserInput input;
    private ResponseReceiver receiver;

    private DataPreprocessor commandParser;
    private DataPreprocessor dataPreprocessor;


    public void run() {
        String userInput;
        do {
            try{
                userInput = input.getLine();

                String command = commandParser.parse(userInput);
                command = commandParser.parse(userInput).isEmpty()? RequestHandlerBuilder.SEND_MESSAGE_COMMAND: command;
                String data = dataPreprocessor.parse(userInput);

                data = DataPreprocessorBuilder.build(command).parse(data);

                RequestHandlerBuilder.build(command).handle(data);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                userInput = new String();
            }
        } while (!EXIT_COMMAND.equalsIgnoreCase(userInput));

        receiver.setWorking(false);
    }
}