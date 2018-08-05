package by.dima.model.entity.command;

import java.net.Socket;

public class LogCommand implements Command{
    @Override
    public void execute(Socket socket, String data) {
        System.out.println(socket.getInetAddress() + "send data: " + data);
    }
}
