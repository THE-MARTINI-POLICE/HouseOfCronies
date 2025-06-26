
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    //The attributes of each client that is connected to the server.
    private Socket clientSocket;
    private int clientNumber;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    //Constructor
    //The socket that is passed into the ClientHandler constructor is created by serverSocket.accpet. 
    //So this socket is a server side socket.And the subroutines evoked on this socket are server side subroutines.
    public ClientHandler(Socket socket,int clientNumber){
        this.clientSocket = socket;
        this.clientNumber = clientNumber;
        try{

            //As explained in the comment above. The subroutines evoked on this socket are server side subroutines.
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            this.bufferedReader = new BufferedReader(inputStreamReader);
            this.bufferedWriter = new BufferedWriter(outputStreamWriter);

        }catch(IOException e){
            System.out.println("Error setting up the client handler:" + e.getMessage());
        }
    }

    //This is the subroutine that will be executed once the thread is started.
    @Override
    public void run(){
        //Add everything that the server should do here...
        try{

            //Welcome message.
            bufferedWriter.write("Welcome to House of Cronies player " + clientNumber + ".");
            bufferedWriter.newLine();
            bufferedWriter.flush();

            //Wait for client messages.
            String inputLine;
            while((inputLine = bufferedReader.readLine()) != null){
                
                if(inputLine.equalsIgnoreCase("BYE")){
                    System.out.println("Client disconnected: " + clientSocket.getInetAddress());
                }

                System.out.println("Received from player "+clientNumber+":"  + inputLine);

                //Process the message and send a response
                String response = "Server received";
                bufferedWriter.write(response);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Sent to client: " + response);
            }

        }catch(IOException e){
            System.out.println("Client disconnected: "+ e.getMessage());
        }finally{

            try{
                if(bufferedWriter != null){
                    bufferedWriter.close();

                }else if(bufferedReader != null){
                    bufferedReader.close();

                }else if(clientSocket != null){
                    clientSocket.close();
                }

            }catch(IOException e){
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }










}