package by.dima.model.logic;

import by.dima.model.entity.Request;
import org.hamcrest.core.StringContains;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestBuilderTest {

    @Test
    public void alreadyRegistered() {
        Request result = RequestBuilder.alreadyRegistered();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.ALREADY_REGISTERED));
    }

    @Test
    public void justRegistered() {
        Request result = RequestBuilder.justRegistered();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.JUST_REGISTERED));
    }

    @Test
    public void lookingForAgent() {
        Request result = RequestBuilder.lookingForAgent();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.LOOKING_FOR_AGENT));
    }

    @Test
    public void connected() {
        Request result = RequestBuilder.connected();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.CONNECTED));
    }

    @Test
    public void clientExited() {
        Request result = RequestBuilder.clientExited();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.CLIENT_LEFT));
    }
}