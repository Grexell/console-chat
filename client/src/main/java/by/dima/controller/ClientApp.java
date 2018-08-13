package by.dima.controller;

import by.dima.model.logic.MessageSender;
import by.dima.model.logic.impl.StreamMessageSender;
import by.dima.model.entity.Client;
import by.dima.model.logic.*;
import by.dima.util.ConsoleUserOutput;
import by.dima.util.UserInput;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) {
        try {
            new ClientApp().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String EXIT_COMMAND = "/exit";

    public ClientApp() throws IOException {
        input = UserInput.console();
        client = new Client();
        messageSender = new StreamMessageSender(client.getSocket().getOutputStream());
        requestPreprocessor = new CommandPreprocessor();
        receiver = new ResponseReceiver(client.getSocket().getInputStream(), new ConsoleUserOutput());
        new Thread(receiver).start();
    }

    private Client client;
    private UserInput input;
    private ResponseReceiver receiver;
    private MessageSender messageSender;
    private RequestPreprocessor requestPreprocessor;

    public void run() {
        String userInput;
        do {
            try{
                userInput = input.getLine();
                String userRequest = requestPreprocessor.process(userInput);
                messageSender.send(userRequest);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                userInput = new String();
            }
        } while (!EXIT_COMMAND.equalsIgnoreCase(userInput));

        receiver.setWorking(false);

        try {
            client.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}