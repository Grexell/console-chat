package by.dima.model.logic;

import by.dima.model.entity.Server;
import by.dima.model.logic.impl.StreamMessageReceiver;
import by.dima.util.JasonObjectConverter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler {

    public void handle(Server server) {
        ServerSocket sSocket = server.getServerSocket();
        while (server.isWorking()) {
            try {
                Socket socket = sSocket.accept();
                new Thread(new RequestController(socket, new StreamMessageReceiver(socket.getInputStream()), new JasonObjectConverter())).start();
            } catch (IOException e) {
                System.out.println(1);
                e.printStackTrace();
            }
        }
    }
}