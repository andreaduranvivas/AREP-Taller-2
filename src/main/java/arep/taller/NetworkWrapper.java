package arep.taller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public interface NetworkWrapper {
    BufferedReader getReader(InputStream inputStream) throws IOException;
    OutputStream getOutputStream(Socket socket) throws IOException;
    Socket accept(ServerSocket serverSocket) throws IOException;
}
