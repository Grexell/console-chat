package by.dima.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PageCalculatorTest {

    @Test
    public void getStartIndex() {
        int expected = 15;
        assertEquals(PageCalculator.getStartIndex(1, 15), expected);
    }
}