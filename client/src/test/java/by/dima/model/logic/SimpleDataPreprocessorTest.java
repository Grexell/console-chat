package by.dima.model.logic;

import by.dima.model.logic.preprocessor.impl.SimpleDataPreprocessor;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleDataPreprocessorTest {

    @Test
    public void process() {
        String data = "data";
        assertEquals(new SimpleDataPreprocessor().parse(data), data);
    }
}