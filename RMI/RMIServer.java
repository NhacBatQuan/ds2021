    package RMI_FileTransfer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        FileService fileService = new FileServiceImpl();
        LocateRegistry.createRegistry(6789);
        for(int i = 0;i<1000;i++){
            Naming.rebind("rmi://localhost:6789/"+String.valueOf(i), fileService);

        }
        }
}
