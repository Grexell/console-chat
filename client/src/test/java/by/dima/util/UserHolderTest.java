package by.dima.util;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserHolderTest {

    @Test
    public void getCurrentUser() {
        User user = new User("test", Role.AGENT);
        UserHolder.getInstance().setCurrentUser(user);
        assertEquals(user, UserHolder.getInstance().getCurrentUser());
    }

    @Test
    public void setCurrentUser() {
        User user = new User("test set", Role.AGENT);
        UserHolder.getInstance().setCurrentUser(user);
        assertEquals(user, UserHolder.getInstance().getCurrentUser());
    }
}