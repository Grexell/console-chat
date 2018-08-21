package by.dima.model.logic;

import by.dima.model.entity.Server;
import by.dima.model.logic.impl.StreamMessageReceiver;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler {

    private ObjectConverter converter = new JasonObjectConverter();

    public void handle(Server server) {
        ServerSocket sSocket = server.getServerSocket();
        while (server.isWorking()) {
            try {
                Socket socket = sSocket.accept();
                MessageReceiver receiver = new StreamMessageReceiver(socket.getInputStream());
                new Thread(new RequestController(socket, receiver, converter)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}