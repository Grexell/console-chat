package by.dima.model.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MessageTest {

    Message message;

    @Before
    public void setUp() {
        message = new Message("test", new Date(), new User("client", Role.CLIENT));
    }

    @Test
    public void setText() {
        String newText = "text";
        message.setText(newText);
        assertEquals(message.getText(), newText);
    }

    @Test
    public void setDate() {
        Date newDate = new Date();
        message.setDate(newDate);
        assertEquals(message.getDate(), newDate);
    }

    @Test
    public void setUser() {
        User newUser = new User("New User", Role.AGENT);
        message.setUser(newUser);
        assertEquals(message.getUser(), newUser);
    }
}
