
import java.net.*;
import java.io.*;




public class Server {
    
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader input = null;
        OutputStreamWriter output = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        // Create a server socket on port 12345 outside the loop and use try-with-resources
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                try {
                    // Accept a client connection
                    socket = serverSocket.accept();
                    System.out.println("Client connected: " + socket.getInetAddress());

                    // Set up input and output streams
                    input = new InputStreamReader(socket.getInputStream());
                    output = new OutputStreamWriter(socket.getOutputStream());
                    reader = new BufferedReader(input);
                    writer = new BufferedWriter(output);

                    while (true) {
                        // Read a line from the client
                        String clientMessage = reader.readLine();
                        if (clientMessage == null || clientMessage.equalsIgnoreCase("Bye")) {
                            System.out.println("Client disconnected: " + socket.getInetAddress());
                            break; // Exit the loop if client disconnects or sends "exit"
                        }
                        System.out.println("Received from client: " + clientMessage);
                        // Process the message and send a response
                        String response = "Server received:"; 
                        writer.write(response);
                        writer.newLine(); 
                        writer.flush();
                        System.out.println("Sent to client: " + response);
                    }

                    // Close resources after use
                    writer.close();
                    reader.close();
                    output.close();
                    input.close();
                    socket.close();

                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error setting up server: " + e.getMessage());
        }
    }
}

    
