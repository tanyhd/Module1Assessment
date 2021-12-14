package myserver;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int port = 3000;
        String inputSetting ="";
        String directory = "static";

        HttpServer server = new HttpServer();

        if (args.length != 0) {
            for (int i =0; i < args.length; i++) {
               inputSetting += args[i];
               inputSetting += ",";
            }
            String[] command = inputSetting.trim().split(",");
            for (int i = 0; i < command.length; i++) {
                if (command[i].equals("--port") && (i + 1) < command.length) {
                    port = Integer.parseInt(command[i+1]);
                } else if (command[i].equals("--docRoot") && (i + 1) < command.length) {
                    directory = command[i+1];
                }
            }
        }
        System.out.println("Port set to " + port);
        System.out.println("Directory set to " + directory);
    }
        //server.startServer(args[0], args[1]);  
}
