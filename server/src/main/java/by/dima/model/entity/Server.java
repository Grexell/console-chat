package by.dima.model.entity;

import java.net.ServerSocket;

public class Server {

    private ServerSocket serverSocket;
    private boolean working;

    public Server() {
    }

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Server(ServerSocket serverSocket, boolean working) {
        this.serverSocket = serverSocket;
        this.working = working;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

}
