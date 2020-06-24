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

    private static boolean running = true;

    public static void main(String[] args) {

        try {

            clientSocket = new Socket(IP, PORT);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Scanner in = new Scanner(System.in);

            while(running){

                String msg = in.nextLine();

                if(msg.equals("quit")){
                    stopConnection();
                    return;
                }

                sendMessage(msg);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String sendMessage(String msg) {

        try {

            out.println(msg);
            return in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }

    }

    public static void stopConnection() {
        try {

            in.close();
            out.close();
            clientSocket.close();

            System.out.println("Disconnected..");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
