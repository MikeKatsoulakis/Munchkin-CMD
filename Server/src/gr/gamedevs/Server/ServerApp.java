package gr.gamedevs.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerApp {

    private static final int PORT = 4444;

    //List of connected Clients, currently useless
    private static List<ClientHandler> clientHandlers = new ArrayList<>();

    public static void main(String[] args) {

        try {

            //Starting the server
            start(PORT);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server could not start..");
        }

    }

    private static ServerSocket serverSocket;

    public static void start(int port) throws IOException {

        //Open a server socket
        serverSocket = new ServerSocket(port);

        //Server connections management
        while (true) {

            //Creating a client handler for the connecting client
            ClientHandler ch = new ClientHandler(serverSocket.accept());

            //Adding the handler to the list
            clientHandlers.add(ch);

            //Starting the thread
            ch.start();

        }

    }

    public void stop() throws IOException {
        serverSocket.close();
    }

}
