package by.dima.model.logic;

import by.dima.model.entity.Message;
import by.dima.model.entity.Response;
import by.dima.model.logic.impl.StreamMessageReceiver;
import by.dima.util.*;

import java.io.InputStream;

public class ResponseReceiver implements Runnable {
    private InputStream inputStream;
    private MessageReceiver messageReceiver;
    private UserOutput userOutput;
    private ObjectConverter messageConverter;
    private boolean working;
    private MessageFormatter formatter;

    public ResponseReceiver(InputStream inputStream, UserOutput userOutput) {
        this.inputStream = inputStream;
        this.userOutput = userOutput;
        this.messageReceiver = new StreamMessageReceiver(inputStream);
        this.formatter = new SimpleMessageFormatter();
        working = true;
        this.messageConverter = new JasonObjectConverter();
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    @Override
    public void run() {
        while (working){
            String data = messageReceiver.receive();
            if (data != null) {
                Response response = messageConverter.read(data, Response.class);
                Message message = messageConverter.read(response.getData(), Message.class);
                userOutput.print(formatter.format(message));
            }
        }
    }
}
