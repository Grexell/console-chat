package by.dima.model.logic.impl;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.SequenceInputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class StreamMessageReceiverTest {

    @Test
    public void receive() {
        String msg = "test";
        StreamMessageReceiver receiver = new StreamMessageReceiver(new ByteArrayInputStream(msg.getBytes()));
        assertEquals(receiver.receive(), msg);
    }
}