package by.dima.model.entity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static final String DEFAULT_HOST = "";
    public static final int DEFAULT_PORT = 9999;

    private Socket socket;

    public Client() throws IOException {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public Client(Socket socket) {
        this.socket = socket;
    }

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(InetAddress.getByName(host), port);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
