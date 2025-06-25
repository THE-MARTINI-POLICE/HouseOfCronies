
import java.net.*;
import java.io.*;




public class Server {
    static int Count = 0; // Initialize player count
    
    public static void main(String[] args) {
        // Create a server socket on port 5000 outside the loop and use try-with-resources
        try (ServerSocket serverSocket = new ServerSocket(5000, 50, InetAddress.getByName("192.168.68.63"))) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                try {
                    // Accept a client connection
                    Socket socket = serverSocket.accept();
                    Count++; // Increment player count for each new client
                    System.out.println("Client connected: " + socket.getInetAddress());
                    ClientHandler clientHandler = new ClientHandler(socket, Count);
                    Thread clientThread = new Thread(clientHandler);
                    clientThread.start();
                    
                    // No need to handle the client here - the ClientHandler thread will do it
                    
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error setting up server: " + e.getMessage());
        }
    }
}

    
