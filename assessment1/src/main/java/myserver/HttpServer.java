package myserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public void startServer(String directory1, String directory2, int port) {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started listening...");
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // information
                                                                                                       // get from
                                                                                                       // client
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // information to sent to client

            String text = "";



        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }

    }

}
