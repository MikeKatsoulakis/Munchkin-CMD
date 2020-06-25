package gr.gamedevs.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//Client Handler Class.
//Basically a thread that manages input and output for a connected client.
//Runs asynchronously to the main server.

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    //Constructor
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    //Main thread method
    public void run() {

        try {

            //Initializing the input and output Streams
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            //Continuously waiting for input from the client
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                //Printing the client message to the server console along with the clients info (IP, Port)
                System.out.println(clientSocket + " - " + inputLine);

                //Handling disconnections
                if (inputLine.equals("quit")) {
                    System.out.println(clientSocket + " - Disconnected..");
                    break;
                }

                //Response to client
                out.println(clientSocket + " - " + inputLine);

            }

            //Close the client connection and both the input and output streams
            in.close();
            out.close();
            clientSocket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
