import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

public class FileServiceImpl extends UnicastRemoteObject implements FileService{
    private List<StorageService> StorageList;
    private List<String> ServerLocation;
    private List<String> AvailableFile;
    private Map<String,List<String>> FileLocation;
    public FileServiceImpl(Map<String,List<String>> FileLocation,List<StorageService> StorageList,List<String> ServerLocation,List<String> AvailableFile) throws RemoteException {
        this.StorageList = StorageList;
        this.ServerLocation = ServerLocation;
        this.AvailableFile = AvailableFile;
        this.FileLocation = FileLocation;
    }
    @Override
    public List<String> getAvailableFile() throws RemoteException{
        return AvailableFile;
    }
    @Override
    public void setAvailableFile(List<String> availableFile) throws RemoteException {
        AvailableFile = availableFile;
    }

    public Map<String, List<String>> getFileLocation() throws RemoteException{
        return FileLocation;
    }

    public void setFileLocation(Map<String, List<String>> fileLocation) throws RemoteException{
        FileLocation = fileLocation;
    }

    public String getLocationOfFile(String filename) throws RemoteException{
        List<String> locations =  FileLocation.get(filename);
        if(locations.size()==1) return locations.get(0);
        else{
            int index = (int) Math.random()*(locations.size()) ;
            return locations.get(index);
        }
    }


    @Override
    public List<StorageService> getStorageList() {
        return StorageList;
    }
    @Override
    public List<String> getServerLocation() {
        return ServerLocation;
    }
    @Override
    public void addStorageLocation(String serverName) throws RemoteException{
        this.ServerLocation.add(serverName);
    }
    @Override
    public void removeStorageLocation(String serverName) throws RemoteException{
        this.ServerLocation.remove(serverName);
    }

    @Override
    public StorageService fileAt(String filename) throws RemoteException, MalformedURLException, NotBoundException {
        for(String serverLoc: ServerLocation){
            StorageService server = (StorageService) Naming.lookup("rmi://localhost:6789/"+serverLoc);
            if(server.getFilelist().contains("filename")){
                return server;
            }
        }
        return null;
    }

    @Override
    public byte[] read()  throws RemoteException{
        return new byte[0];
    }
    @Override
    public void write()  throws RemoteException{
    }
    @Override
    public boolean storageExists(String name) throws RemoteException {
        for(StorageService server: StorageList){
            if(server.getServerName().equals(name)) return true;
        }
        return false;
    }

    @Override
    public void addStorage(StorageService storage) throws RemoteException {
        this.StorageList.add(storage);
    }
}
