package myserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    private int port = 3000;
    public void startServer(String directory, int port) {

        System.out.println(directory);
        System.out.println(port);
    }

}
