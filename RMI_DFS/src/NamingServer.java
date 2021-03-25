import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class NamingServer{
    private static Registry registry;
    private static RegisterServiceImpl registerService;
    private static FileService fileService;

    public static void setFileService(FileServiceImpl fileService) {
        NamingServer.fileService = fileService;
    }

    public static void setRegisterService(RegisterServiceImpl registerService) {
        NamingServer.registerService = registerService;
    }
    public static void Initialize() throws RemoteException, MalformedURLException {
        setRegisterService(new RegisterServiceImpl());
        setFileService(new FileServiceImpl(new HashMap<String, List<String>>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
        registry = LocateRegistry.createRegistry(6789);
        Naming.rebind("rmi://localhost:6789/main",fileService);
    }

    public static void createServer(String name, String location) throws MalformedURLException, RemoteException, AlreadyBoundException, NotBoundException {
        StorageServer server = new StorageServer(name, new ArrayList<>(),location);
        fileService = (FileService) Naming.lookup("rmi://localhost:6789/main");
        fileService.addStorage(server);
        fileService.addStorageLocation(name);
        registerService.bind(name,server);
        Naming.rebind("rmi://localhost:6789/main",fileService);
    }



    public static void main(String[] args) throws AlreadyBoundException, RemoteException, MalformedURLException, NotBoundException {
        Initialize();
        FileService fileService = (FileService) Naming.lookup("rmi://localhost:6789/main");
        boolean running = true;
        while(running){
            Scanner sc = new Scanner(System.in);
            System.out.println("Press 0 to exit");
            System.out.println("Press 1 to create a new server");
            System.out.println("Press 2 to see all available server");
            int input = sc.nextInt();
            if(input == 0){
                running = false;
            }
            else if(input == 1){
                System.out.println("Please choose a name for your server");
                Scanner sc2 = new Scanner(System.in);
                String Servername = sc2.nextLine();
                while(fileService.storageExists(Servername)){
                    System.out.println("Server name is already chosen.Choose another name");
                    Servername = sc2.nextLine();
                }
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.showSaveDialog(null);
                String location = f.getSelectedFile().getAbsolutePath();
                createServer(Servername,location);
                fileService = (FileService) Naming.lookup("rmi://localhost:6789/main");
            }
            else if(input == 2){
                for(String server: fileService.getServerLocation()){
                    System.out.println("Server number "+ (fileService.getServerLocation().indexOf(server) + 1) +": " +server);
                }
            }
            else{
                System.out.println("Please insert again");
            }
        }
    }

}
