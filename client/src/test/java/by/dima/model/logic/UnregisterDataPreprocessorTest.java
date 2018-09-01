package by.dima.model.logic;

import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.model.logic.preprocessor.impl.MessageDataPreprocessor;
import by.dima.util.JasonObjectConverter;
import by.dima.util.UserHolder;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnregisterDataPreprocessorTest {

    @Test
    public void process() {
        User user = new User("test", Role.CLIENT);
        UserHolder.getInstance().setCurrentUser(user);
        new MessageDataPreprocessor(new JasonObjectConverter()).parse("data");
        assertNull(UserHolder.getInstance().getCurrentUser());
    }
}