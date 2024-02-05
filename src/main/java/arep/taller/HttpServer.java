package arep.taller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpServer {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean firstLine = true;
            String uriStr = "";

            while ((inputLine = in.readLine()) != null) {
                if(firstLine){
                    uriStr = inputLine.split(" ")[1];
                    firstLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            URI requestUri = new URI(uriStr);

            try {
                outputLine = htttpResponse(requestUri);
            }catch (Exception e){
                outputLine = httpError();
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static String httpError() {
        String outputLine = "HTTP/1.1 400 Not Found\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Error Not found</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Error</h1>\n"
                + "    </body>\n";
        return outputLine;

    }

    public static String htttpResponse(URI requestedURI) throws IOException {

        Path file = Paths.get("target/classes/public" + requestedURI.getPath());
        StringBuilder outputLine = new StringBuilder();
        outputLine.append("HTTP/1.1 200 OK\r\n");

        if (Files.isRegularFile(file)) {
            String mimeType = Files.probeContentType(file);
            byte[] fileBytes = Files.readAllBytes(file);

            outputLine.append("Content-Type: ").append(mimeType).append("\r\n");
            outputLine.append("\r\n");

            // Agrega los bytes directamente al contenido de la respuesta
            for (byte b : fileBytes) {
                outputLine.append((char) (b & 0xFF));
            }

        } else {
            StringBuilder content = new StringBuilder();
            BufferedReader reader = Files.newBufferedReader(file);
            String line = null;

            outputLine.append("Content-Type:text/html\r\n").append("\r\n");
            while ((line = reader.readLine())!= null){
                System.out.println(line);
                content.append(line).append("\n");
            }

            outputLine.append(content);
        }

        return outputLine.toString();

    }

}