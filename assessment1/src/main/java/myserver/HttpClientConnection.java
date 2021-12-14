package myserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class HttpClientConnection implements Runnable {
    private Socket socket;
    boolean resourceExists = false;

    public HttpClientConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        String[] line;

        try {
            HttpWriter httpWriter = new HttpWriter(this.socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            line = in.readLine().split(" ");

            System.out.println(line);
            if (line[0].equals("GET")) {
                File fileLocation = new File(line[1]);
                if (fileLocation.exists()) {
                    httpWriter.writeString("HTTP/1.1 200 OK\r\n");
                    httpWriter.writeString("\r\n");
                    httpWriter.writeString("File found");
                } else {
                    httpWriter.writeString("HTTP/1.1 404 Not Found\r\n");
                    httpWriter.writeString("\r\n");
                    httpWriter.writeString(line[1] + " not supported\r\n");
                }
            } else {
                httpWriter.writeString("HTTP/1.1 405 Method Not Allowed\r\n");
                httpWriter.writeString("\r\n");
                httpWriter.writeString(line[0] + " not supported\r\n");
            }


        } catch (IOException ioe) {
            System.out.println("Something went wrong..");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean checkFile (String file) {
        File fileLocation = new File(file);
        return fileLocation.exists();
    }

}
