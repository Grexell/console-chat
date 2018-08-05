package by.dima.model.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable {

    private Thread thread;
    private Socket socket;

    public SocketHandler(Socket socket) {
        this.socket = socket;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            while(socket.isConnected()){
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
