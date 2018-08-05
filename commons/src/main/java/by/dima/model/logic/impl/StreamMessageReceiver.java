package by.dima.model.logic.impl;

import by.dima.model.logic.MessageReceiver;

import java.io.InputStream;
import java.util.Scanner;

public class StreamMessageReceiver implements MessageReceiver {

    private InputStream inputStream;
    private Scanner scanner;

    public StreamMessageReceiver(InputStream inputStream) {
        this.inputStream = inputStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public String receive() {
        String result = null;
        if (scanner.hasNextLine()) {
            result = scanner.nextLine();
        }
        return result;
    }
}
