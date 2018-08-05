package by.dima.model.logic;

import by.dima.model.entity.Request;
import by.dima.model.entity.command.Command;
import by.dima.util.ObjectConverter;
import java.net.Socket;

public class RequestController implements Runnable {
    private Socket socket;
    private MessageReceiver receiver;
    private ObjectConverter requestConverter;

    public RequestController(Socket socket, MessageReceiver receiver, ObjectConverter converter) {
        this.socket = socket;
        this.receiver = receiver;
        this.requestConverter = converter;
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            String msg = receiver.receive();
            if (msg != null) {
                Request request = requestConverter.read(msg, Request.class);
                Command command = CommandBuilder.getCommand(request.getCommand());
                command.execute(socket, request.getData());
            }
        }
    }
}