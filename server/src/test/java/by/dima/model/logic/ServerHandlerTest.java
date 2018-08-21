package by.dima.model.logic;

import by.dima.model.entity.Server;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServerHandlerTest {

    @Test
    public void handle() throws IOException, InterruptedException {
        ServerSocket sSocket = mock(ServerSocket.class);
        Socket socket = mock(Socket.class);
        when(sSocket.accept()).thenReturn(socket);
        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream("test".getBytes()));

        Server s = new Server(sSocket, true);
        new Thread(()->{
            ServerHandler handler = new ServerHandler();
            handler.handle(s);
        }).start();
        Thread.sleep(1000);
        s.setWorking(false);

        verify(sSocket, atLeastOnce()).accept();
        verify(socket, atLeastOnce()).getInputStream();
    }
}