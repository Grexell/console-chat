package by.dima.model.logic;

import by.dima.model.entity.Request;
import by.dima.model.entity.Role;
import by.dima.model.entity.User;
import by.dima.util.JasonObjectConverter;
import by.dima.util.ObjectConverter;
import org.junit.Test;
import org.mockito.Mockito;

import javax.xml.soap.SOAPConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RequestControllerTest {

    private ObjectConverter converter = new JasonObjectConverter();

    @Test
    public void run() throws IOException, InterruptedException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.isConnected()).thenReturn(true);
        when(socket.getOutputStream()).thenReturn(stream);

        MessageReceiver receiver = mock(MessageReceiver.class);
        when(receiver.receive()).thenReturn(converter.write(new Request("register", converter.write(new User("test", Role.CLIENT)))));

        new Thread(new RequestController(socket, receiver, converter)).start();
        Thread.sleep(1000);

        when(socket.isConnected()).thenReturn(false);

        verify(socket, atLeastOnce()).getOutputStream();
        verify(receiver, atLeastOnce()).receive();
    }
}