import java.io.*;
import java.net.Socket;

public class ClientTCP {
    public static void main(String args[]) throws Exception{
        String filename = "D:\\Java Project\\DFSCourse_Lab1\\src\\sample.txt";
        String line = null;
        String input = "";
        String output = "";
        FileReader fileReader = new FileReader(filename);
        BufferedReader ReadFile = new BufferedReader(fileReader);
        while((line = ReadFile.readLine())!=null){
            input+=line+"\n";
        }
        Socket clientSocket = new Socket("127.0.0.1",12345);
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(input + '\n');
        System.out.println(input);
        BufferedReader inFromServer =
                new BufferedReader(new
                        InputStreamReader(clientSocket.getInputStream()));
        line = null;
        PrintWriter writer = new PrintWriter(new FileWriter("D:\\Java Project\\DFSCourse_Lab1\\src\\sampleoutput.txt"));
        while((line = inFromServer.readLine())!=null){
            if(line.isEmpty()) {
                break;
            }
            output+=line+"\n";
            }
        System.out.println("Finish");
        writer.write(output);
        writer.close();
        clientSocket.close();

    }
}
