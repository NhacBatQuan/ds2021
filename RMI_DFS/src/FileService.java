import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface FileService extends Remote {
    byte[] read() throws RemoteException;
    void write() throws RemoteException;

    boolean storageExists(String name) throws RemoteException;


    void addStorage(StorageService storage) throws RemoteException;

    List<String> getAvailableFile() throws RemoteException;

    void setAvailableFile(List<String> availableFile) throws RemoteException;

    Map<String, List<String>> getFileLocation() throws RemoteException;

    List<StorageService> getStorageList() throws RemoteException;

    List<String> getServerLocation() throws RemoteException;

    void addStorageLocation(String serverName) throws RemoteException;

    StorageService fileAt(String filename) throws RemoteException, MalformedURLException, NotBoundException;

    void setFileLocation(Map<String, List<String>> fileLocation) throws RemoteException;

    String getLocationOfFile(String filename) throws RemoteException;

}
