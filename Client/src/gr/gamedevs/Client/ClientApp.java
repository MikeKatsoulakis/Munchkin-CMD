package gr.gamedevs.Client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    private static final String IP = "127.0.0.1";
    private static final int PORT = 4444;

    public static void main(String[] args) {

        try {

            //Initializing the Client Socket and the input and output Streams
            clientSocket = new Socket(IP, PORT);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //Scanner to get the user's input
            Scanner in = new Scanner(System.in);

            //Basic One-Way messaging and disconnecting logic
            while(true){

                String msg = in.nextLine();
                sendMessage(msg);

                //Disconnecting the client if 'quit' is send as an input
                if(msg.equals("quit")){
                    stopConnection();
                    return;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendMessage(String msg) {

        try {

            //Send message to server
            out.println(msg);

            //Server response
            in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void stopConnection() {
        try {

            //Close the client connection and both the input and output streams
            in.close();
            out.close();
            clientSocket.close();

            System.out.println("Disconnected..");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
