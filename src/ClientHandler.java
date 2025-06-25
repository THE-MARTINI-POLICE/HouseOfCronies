
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    public int playerCount;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ClientHandler(Socket socket, int Count) {
        this.clientSocket = socket;
        this.playerCount = Count;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            this.bufferedReader = new BufferedReader(inputStreamReader);
            this.bufferedWriter = new BufferedWriter(outputStreamWriter);
        } catch (IOException e) {
            System.out.println("❌ Error setting up client handler: " + e.getMessage());
        }
    }
    

    @Override
    public void run() {
        try {
            // Send welcome message
            bufferedWriter.write("Welcome to House of Cronies!");
            switch (playerCount) {
                case 1:
                    bufferedWriter.write(" You are Player 1.");
                    break;
                case 2:
                    bufferedWriter.write(" You are Player 2.");
                    break;
                default:
                    bufferedWriter.write(" You are Player " + playerCount + ".");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();

            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("Bye")) {
                    System.out.println("Client disconnected: " + clientSocket.getInetAddress());
                    break;
                }

                
                System.out.println("Received from client: " + inputLine);
                
                // Process the message and send a response
                String response = "Server received";
                bufferedWriter.write(response);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Sent to client: " + response);

                // TODO: Add command parsing here later
            }
        } catch (IOException e) {
            System.out.println("💀 Client disconnected: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
