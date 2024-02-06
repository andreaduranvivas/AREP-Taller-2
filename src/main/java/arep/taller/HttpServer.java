package arep.taller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The main function for the Java program, which sets up a server socket and handles incoming client requests.
 *
 * @throws IOException       if an I/O error occurs
 * @throws URISyntaxException if a string could not be parsed as a URI reference
 */
public class HttpServer {

    private final NetworkWrapper networkWrapper;

    public HttpServer(NetworkWrapper networkWrapper) {
        this.networkWrapper = networkWrapper;
    }
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

            OutputStream outputStream = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            boolean firstLine = true;
            String uriStr = "";

            while (true) {
                String inputLine = in.readLine();
                if (inputLine == null || inputLine.isEmpty()) {
                    break;
                }

                if (firstLine) {
                    uriStr = inputLine.split(" ")[1];
                    firstLine = false;
                }
                System.out.println("Received: " + inputLine);
            }

            URI requestUri = new URI(uriStr);

            try {
                byte[] outputBytes = htttpResponse(requestUri);
                outputStream.write(outputBytes);
            } catch (Exception e) {
                byte[] errorBytes = httpError();
                outputStream.write(errorBytes);
            }

            outputStream.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    /**
     * Generates an HTTP response based on the requested URI.
     *
     * @param  requestedURI   the URI that was requested
     * @return                the HTTP response as a byte array
     */
    public static byte[] htttpResponse(URI requestedURI) throws IOException {
        Path file = Paths.get("target/classes/public" + requestedURI.getPath());

        if (Files.isRegularFile(file)) {
            String mimeType = Files.probeContentType(file);
            byte[] fileBytes = Files.readAllBytes(file);

            ByteArrayOutputStream response = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(response);

            dos.writeBytes("HTTP/1.1 200 OK\r\n");
            dos.writeBytes("Content-Type: " + mimeType + "\r\n");
            dos.writeBytes("Content-Length: " + fileBytes.length + "\r\n");
            dos.writeBytes("\r\n");
            dos.write(fileBytes, 0, fileBytes.length);

            return response.toByteArray();
        } else {
            return httpError();
        }
    }

    /**
     * Generates an HTTP error response with status code 404 Not Found
     *
     * @return          the HTTP error response as a byte array
     */
    private static byte[] httpError() {
        String errorResponse = "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Error Not found</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Error</h1>\n" +
                "    </body>\n" +
                "</html>";

        return errorResponse.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Extracts the movie title from the given URI.
     *
     * @param  uri  the URI containing the movie title
     * @return      the extracted movie title, or null if it cannot be extracted
     */
    public static String extractMovieTitleFromUri(String uri) {
        String[] params = uri.split("\\?");
        if (params.length == 2) {
            String[] keyValue = params[1].split("=");
            if (keyValue.length == 2 && keyValue[0].equals("title")) {
                return keyValue[1];
            }
        }
        return "";
    }

}