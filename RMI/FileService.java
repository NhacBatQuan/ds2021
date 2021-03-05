package RMI_FileTransfer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileService extends Remote {
    public void upload(String filename, byte[] file) throws RemoteException;

}
