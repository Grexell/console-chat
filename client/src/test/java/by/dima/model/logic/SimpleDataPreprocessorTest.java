package by.dima.model.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleDataPreprocessorTest {

    @Test
    public void process() {
        String data = "data";
        assertEquals(new SimpleDataPreprocessor().process(data), data);
    }
}