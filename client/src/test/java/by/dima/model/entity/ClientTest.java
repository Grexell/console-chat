package by.dima.model.entity;

import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void getSocket() {
        Client client = new Client(new Socket());
        assertNotNull(client.getSocket());
    }

    @Test
    public void setSocket() throws IOException {
        Socket socket = new Socket();
        Client client = new Client(null);
        client.setSocket(socket);
        assertEquals(client.getSocket(), socket);
    }
}