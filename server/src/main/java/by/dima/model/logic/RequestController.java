package by.dima.model.logic;

import by.dima.model.entity.Response;
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
                Response response = requestConverter.read(msg, Response.class);
                Command command = CommandBuilder.getCommand(response.getCommand());
                command.execute(socket, response.getData());
            }
        }
    }
}