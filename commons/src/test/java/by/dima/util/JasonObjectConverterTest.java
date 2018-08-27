package by.dima.util;

import by.dima.model.entity.Response;
import org.hamcrest.core.StringContains;
import org.junit.Test;

import static org.junit.Assert.*;

public class JasonObjectConverterTest {

    private Response obj = new Response("test command", "test data");

    @Test
    public void write() {
        String res = new JasonObjectConverter().write(obj);
        assertThat(res, new StringContains(obj.getCommand()));
        assertThat(res, new StringContains(obj.getData()));
    }

    @Test
    public void read() {
        String data = "{" +
                "\"command\": \"" + obj.getCommand() + "\"," +
                "\"data\": \"" + obj.getData() + "\"" +
                "}";
        Response response = new JasonObjectConverter().read(data, Response.class);
        assertEquals(response.getCommand(), obj.getCommand());
        assertEquals(response.getData(), obj.getData());
    }
}