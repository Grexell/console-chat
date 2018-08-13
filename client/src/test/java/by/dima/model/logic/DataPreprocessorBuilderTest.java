package by.dima.model.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataPreprocessorBuilderTest {

    @Test
    public void build() {
        assertNotNull(DataPreprocessorBuilder.build(DataPreprocessorBuilder.EXIT));
        assertNotNull(DataPreprocessorBuilder.build(DataPreprocessorBuilder.LEAVE));
        assertNotNull(DataPreprocessorBuilder.build(DataPreprocessorBuilder.REGISTER));
        assertNotNull(DataPreprocessorBuilder.build("test"));
    }
}