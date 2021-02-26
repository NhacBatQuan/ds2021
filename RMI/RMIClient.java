package RMI;

import java.io.*;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Scanner;

public class RMIClient {
    private static Registry registry;

    public static void main(String[] args) throws IOException, AlreadyBoundException, NotBoundException {
        registry = LocateRegistry.getRegistry("6789");
        while(true){
            System.out.println("Press 0 to exit");
            System.out.println("Press 1 to upload file");
            System.out.println("Press 2 to download file");
            Scanner sc = new Scanner(System.in);
            Integer input = sc.nextInt();
            if(input == 0){
                break;
            }
            else if(input == 1){
                System.out.println("Please fill in the file name");
                String filename = sc.nextLine();
                Upload(filename);
            }
            else if(input == 2){
                System.out.println("Please fill in the file name");
                String filename = sc.nextLine();
                RMI_Interface content = (RMI_Interface) Naming.lookup("rmi://localhost:6789/"+filename);
                RMIFile file = content.getFile();
                OutputStream os = new FileOutputStream("F:\\Java-DFS\\src\\RMI\\"+file.getName());
                os.write(file.getContent());
            }

        }
    }

    public static void Upload(String filename) throws IOException, AlreadyBoundException {
        LocalDate date = LocalDate.now();
        DataInputStream reader = new DataInputStream(new FileInputStream("F:\\Java-DFS\\src\\RMI\\"+filename));
        int nBytesToRead = reader.available();
        byte[] bytes = new byte[nBytesToRead];
        reader.read(bytes);
        RMIFile file = new RMIFile(filename,bytes,date);
        RMI_Implement implement = new RMI_Implement();
        implement.setFile(file);
        Naming.bind("rmi://localhost:6789/"+filename,implement);
    }


}

