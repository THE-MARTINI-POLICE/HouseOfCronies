package server;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client{
    public static void main(String[] args){

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("192.168.68.61", 5000);
            
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while(true){

                String msgToSend = scanner.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush( );

                System.out.println(bufferedReader.readLine());

                //Message that disconnects the client from the server.
                if(msgToSend.equalsIgnoreCase("BYE")){
                    break;
                } 
            }
            scanner.close();
        
        //If the Socket connection can not be established.
        }catch(IOException e){
            e.printStackTrace(); 
        } finally{
            try{
                if(socket != null){
                    socket.close();
                }
                else if(inputStreamReader != null) {
                    inputStreamReader.close();
                }
                else if(outputStreamWriter != null){
                    outputStreamWriter.close();
                }
                else if(bufferedReader != null){
                    bufferedReader.close();
                }
                else if(bufferedWriter != null){
                    bufferedWriter.close();
                }
                else{
                    //Do nothing
                }
            }catch(IOException e){
                System.err.println("Failed to close connection.");
            }
        }
    }
}
