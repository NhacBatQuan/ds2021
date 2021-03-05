package RMI_FileTransfer;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class RMIClient {
    public RMIClient(){

    }
    public static void main(String[] args) {
        try{
            while(true){
                Scanner sc = new Scanner(System.in);
                System.out.println("Press 0 to exit");
                System.out.println("Press 1 to upload a file");
                String input = sc.nextLine();
                if(input.equals("0")){
                    break;
                }
                else if (input.equals("1")) {
                    Upload();
                }
            }
            } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void Upload() throws RemoteException, NotBoundException, MalformedURLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the filename");
        String filename = sc.nextLine();
        File directory=new File("F:\\Java-DFS\\src\\RMI\\Server");
        int fileCount=directory.list().length;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        String path = fileChooser.getSelectedFile().getAbsolutePath();
        String extension = "";

        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i+1);
        }
        FileService fileService =(FileService) Naming.lookup("rmi://localhost:6789/"+String.valueOf(fileCount+1));
        fileService.upload("F:\\Java-DFS\\src\\RMI\\Server\\"+filename+extension,new RMIClient().loadByte(path));
    }


    private static byte[] loadByte(String filename){
        byte[] b = null;
        try {
            File file = new File(filename);
            b = new byte[(int) file.length()];
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            is.read(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;

    }
}
