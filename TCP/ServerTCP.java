import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerTCP {
    public static void main(String args[]) throws Exception{
        String input="";
        String to_client;
        String line = null;

        while(true){
            ServerSocket welcomeSocket = new ServerSocket(12345);

            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            while((line = inFromClient.readLine())!=null){
                if(line.isEmpty()){
                    break;
                }
                input+=line+"\n";
                outToClient.flush();
            }
            to_client = input +"(Server accepted!)" + '\n';

            outToClient.writeBytes(to_client);
            welcomeSocket.close();
            connectionSocket.close();
        }
    }
}
