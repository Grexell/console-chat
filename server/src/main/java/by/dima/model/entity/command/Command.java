package by.dima.model.entity.command;

import java.net.Socket;

public interface Command {
    void execute(Socket socket, String data);
}
