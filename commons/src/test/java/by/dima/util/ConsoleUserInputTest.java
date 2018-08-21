package by.dima.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ConsoleUserInputTest {

    private String message = "test";
    private InputStream buff;
    @Before
    public void setUp(){
        buff = System.in;
        System.setIn(new ByteArrayInputStream((message + System.lineSeparator()).getBytes()));
    }

    @After
    public void tearDown(){
        System.setIn(buff);
    }

    @Test
    public void getLine() {
        assertEquals(new ConsoleUserInput().getLine(), message);
    }
}