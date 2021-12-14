package myserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.directory.DirContext;

public class HttpServer {

    private Socket socket;
    ServerSocket serverSocket;

    public void startServer(String directory1, String directory2, int port) throws IOException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening at port " + port);

        if (!checkDirectory(directory1)) {
            System.out.println(directory1 + " directory not exist");
            System.exit(1);
        } else if (!directory2.equals("")) {
            directory2 = "./" + directory2;
            if (!checkDirectory(directory2)) {
                System.out.println(directory2 + " directory does not exist");
                System.exit(1);
            }
        } 

        try {
            while(true) {
                socket = serverSocket.accept();
                HttpClientConnection worker = new HttpClientConnection(socket);
                threadPool.submit(worker);
            }
        } finally {
            serverSocket.close();
        }
    }

    public boolean checkDirectory (String directory) {
        File directoryLocation = new File(directory);
        return directoryLocation.exists();
    }


}
