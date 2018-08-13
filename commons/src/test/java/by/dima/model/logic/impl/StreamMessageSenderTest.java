package by.dima.model.logic.impl;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class StreamMessageSenderTest {

    @Test
    public void send() {
        String msg = "text";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        StreamMessageSender sender = new StreamMessageSender(stream);
        sender.send(msg);
        assertEquals(msg, stream.toString().trim());
    }
}