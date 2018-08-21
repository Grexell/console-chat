package by.dima.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ConsoleUserOutputTest {

    private String message = "test";
    private PrintStream buff;
    private ByteArrayOutputStream stream;
    @Before
    public void setUp(){
        buff = System.out;
        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
    }

    @After
    public void tearDown(){
        System.setOut(buff);
    }


    @Test
    public void print(){
        new ConsoleUserOutput().print(message);
        assertEquals(stream.toString(), message + System.lineSeparator());
    }
}