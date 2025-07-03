package server;

import java.net.*;
import java.io.*;


public class Server {
    static int Count = 0; // Initialize player count
    
    public static void main(String[] args) {
        // Create a server socket on port 5000 outside the loop and use try-with-resources
        try (ServerSocket serverSocket = new ServerSocket(5000, 50, InetAddress.getByName("192.168.68.61"))) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                try {
                    //Listens on port 5000 to establish connection to client.
                    Socket socket = serverSocket.accept();
                    Count++; // Increment count for each new client
                    System.out.println("Client connected: " + socket.getInetAddress());
                    ClientHandler clientHandler = new ClientHandler(socket, Count);
                    Thread clientThread = new Thread(clientHandler); //The class that implements Runnable is passed into the Thread constructor to construct a new Thread object.
                    clientThread.start(); //The run() subroutine of each Thread starts executing.
                
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error setting up server: " + e.getMessage());
        }
    }
}

    
