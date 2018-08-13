package by.dima.util;

import by.dima.model.entity.Message;
import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class SimpleMessageFormatterTest {

    @Test
    public void format() {
        Message message = new Message("text", new Date(), new User("user", Role.CLIENT));
        assertEquals(new SimpleMessageFormatter().format(message), message.getDate() + " | " + message.getUser().getUsername() + " | " + message.getText());
    }
}