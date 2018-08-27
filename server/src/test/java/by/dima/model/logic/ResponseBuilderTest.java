package by.dima.model.logic;

import by.dima.model.entity.Response;
import org.hamcrest.core.StringContains;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseBuilderTest {

    @Test
    public void alreadyRegistered() {
        Response result = RequestBuilder.alreadyRegistered();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.ALREADY_REGISTERED));
    }

    @Test
    public void justRegistered() {
        Response result = RequestBuilder.justRegistered();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.JUST_REGISTERED));
    }

    @Test
    public void lookingForAgent() {
        Response result = RequestBuilder.lookingForAgent();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.LOOKING_FOR_AGENT));
    }

    @Test
    public void connected() {
        Response result = RequestBuilder.connected();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.CONNECTED));
    }

    @Test
    public void clientExited() {
        Response result = RequestBuilder.clientExited();
        assertEquals(result.getCommand(), "message");
        assertThat(result.getData(), new StringContains(RequestBuilder.CLIENT_LEFT));
    }
}