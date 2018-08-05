package by.dima.controller;

import by.dima.model.entity.Server;
import by.dima.model.logic.ServerHandler;
import by.dima.util.UserInput;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerApp {
    public static void main(String[] args) {
        new ServerApp().run();
    }

    public static final String EXIT_COMMAND = "q";

    public ServerApp() {
        input = UserInput.console();
    }

    private UserInput input;

    public void run(){
        try {
            Server server = new Server(new ServerSocket(9999), true);
            new ServerHandler().handle(server);

            String userInput;

            do{
                userInput = input.getLine();
            } while(!EXIT_COMMAND.equalsIgnoreCase(userInput));

            server.setWorking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
