package by.dima.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErrorHandlerTest {

    @Test
    public void handle() {
        String message = "test exception";
        Exception e = new Exception(message);
        assertEquals(new ErrorHandler().handle(e), e.getMessage());
    }
}