package by.dima.util;

import by.dima.model.entity.Request;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JasonObjectConverterTest {

    private Request obj = new Request("test command", "test data");

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
        Request request = new JasonObjectConverter().read(data, Request.class);
        assertEquals(request.getCommand(), obj.getCommand());
        assertEquals(request.getData(), obj.getData());
    }
}