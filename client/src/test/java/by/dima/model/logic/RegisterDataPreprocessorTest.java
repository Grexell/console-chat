package by.dima.model.logic;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterDataPreprocessorTest {

    @Test
    public void process() {
        ObjectConverter converter = new JasonObjectConverter();
        RegisterDataPreprocessor preprocessor = new RegisterDataPreprocessor(converter);
        assertEquals(preprocessor.process("client client"), converter.write(new User("client", Role.CLIENT)));
    }
}