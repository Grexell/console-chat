package by.dima.model.logic.impl;

import by.dima.model.logic.MessageSender;

import java.io.OutputStream;
import java.io.PrintStream;

public class StreamMessageSender implements MessageSender {

    private OutputStream outputStream;

    public StreamMessageSender(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void send(String message) {
        new PrintStream(outputStream).println(message);
    }
}
