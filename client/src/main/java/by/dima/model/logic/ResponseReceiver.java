package by.dima.model.logic;

import by.dima.model.logic.impl.StreamMessageReceiver;
import by.dima.util.ObjectConverter;
import by.dima.util.UserOutput;

import java.io.InputStream;

public class ResponseReceiver implements Runnable {
    private InputStream inputStream;
    private MessageReceiver messageReceiver;
    private UserOutput userOutput;
    private ObjectConverter messageConverter;
    private boolean working;

    public ResponseReceiver(InputStream inputStream, UserOutput userOutput) {
        this.inputStream = inputStream;
        this.userOutput = userOutput;
        this.messageReceiver = new StreamMessageReceiver(inputStream);
        working = true;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    @Override
    public void run() {
        while (working){
            userOutput.print(messageReceiver.receive());
//            userOutput.print(messageConverter.read(messageReceiver.receive(), Message.class).getText());
        }
    }
}
