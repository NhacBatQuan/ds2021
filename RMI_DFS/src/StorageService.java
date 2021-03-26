import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StorageService extends Remote {
    byte[] read(String filename) throws IOException, RemoteException;

    void write(String filename,byte[] contents) throws RemoteException, FileNotFoundException, IOException;

    List<String> getFilelist() throws RemoteException;

    String getServerName() throws RemoteException;

    void addFile(String filename) throws RemoteException;
}
