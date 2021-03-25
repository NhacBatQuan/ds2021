import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, NotBoundException {
        while(true){
            FileService fileService= (FileService) Naming.lookup("rmi://localhost:6789/main");
            Scanner sc = new Scanner(System.in);
            System.out.println("Press 0 to exit");
            System.out.println("Press 1 to see available file");
            System.out.println("Press 2 to upload a file");
            System.out.println("Press 3 to download a file");
            String input = sc.nextLine();
            if(input.equals("0")){
                break;
            }
            else if (input.equals("1")) {
                for(String file: fileService.getAvailableFile()){
                    String ListofLoc = file + " in ";
                    for(String location: fileService.getFileLocation().get(file)){

                        ListofLoc+=location+ " ";
                    }
                System.out.println(ListofLoc);
                }
            }
            else if (input.equals("2")){
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Choose a file name");
                String name = sc2.nextLine();
                int rand1 = (int)(Math.random() * (fileService.getServerLocation().size()));
                int rand2 = (int)(Math.random() * (fileService.getServerLocation().size()));
                while(rand1 == rand2){
                    rand2 = (int)(Math.random() * (fileService.getServerLocation().size()));
                }
                String server1name = fileService.getServerLocation().get(rand1);
                String server2name = fileService.getServerLocation().get(rand2);
                StorageService server1 = (StorageService) Naming.lookup("rmi://localhost:6789/"+server1name);
                StorageService server2 = (StorageService) Naming.lookup("rmi://localhost:6789/"+server2name);
                byte[] contents = fileUploaded();
                server1.write(name,contents);
                server2.write(name,contents);
                ArrayList<String> tempFileList = (ArrayList<String>) fileService.getAvailableFile();
                tempFileList.add(name);
                fileService.setAvailableFile(tempFileList);
                HashMap<String,List<String>> tempMap = (HashMap<String, List<String>>) fileService.getFileLocation();
                ArrayList<String> tempList = new ArrayList<>();
                tempList.add(server1name);
                tempList.add(server2name);
                tempMap.put(name,tempList);
                fileService.setFileLocation(tempMap);
                Naming.rebind("rmi://localhost:6789/main",fileService);
            }
            else if(input.equals("3")){
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Choose the file you want to download");
                String name = sc2.nextLine();
                if(!fileService.getAvailableFile().contains(name)){
                    System.out.println("File not found");
                }
                else{
                    String serverName = fileService.getLocationOfFile(name);
                    System.out.println("Downloading file " + name+ " from "+serverName);
                    StorageService service = (StorageService) Naming.lookup("rmi://localhost:6789/"+serverName);
                    byte[] contents = service.read(name);
                    fileDownloaded(name,contents);
                }
            }
            else{
                System.out.println("Type again");
            }

        }
    }

    public static byte[] fileUploaded() throws IOException {
        byte[] contents = null;
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        f.setCurrentDirectory(new File(System.getProperty("user.dir")));
        f.showSaveDialog(null);
        String location = f.getSelectedFile().getAbsolutePath();
        File file = new File(location);
        contents = new byte[(int) file.length()];
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
        is.read(contents);
        return contents;
    }
    public static void fileDownloaded(String filename,byte[] contents) throws IOException {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        f.setCurrentDirectory(new File(System.getProperty("user.dir")));
        f.showSaveDialog(null);
        String location = f.getSelectedFile().getAbsolutePath();
        File file = new File(location+"/"+filename);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        os.write(contents);
    }
}
