import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class StorageServer extends UnicastRemoteObject implements StorageService {
    private List<String> Filelist;
    private String StorageLocation;
    private String ServerName;

    public StorageServer(String ServerName,List<String> Filelist,String StorageLocation) throws RemoteException {
        super();
        this.ServerName = ServerName;
        this.Filelist = Filelist;
        this.StorageLocation = StorageLocation;
    }
    public StorageServer() throws RemoteException {
        super();
    }

    public String getServerName() throws RemoteException{
        return ServerName;
    }

    @Override
    public void addFile(String filename) throws RemoteException {
        Filelist.add(filename);
    }

    public List<String> getFilelist() throws RemoteException {
        return Filelist;
    }



    public String getStorageLocation() {
        return StorageLocation;
    }
    public boolean checkFileExists(String filename){
        File file = new File(StorageLocation+"/filename");
        if(file.exists()) return true;
        return false;
    }

    @Override
    public byte[] read(String filename) throws IOException, RemoteException {
        byte[] contents = null;
        File file = new File(getStorageLocation()+"/"+filename);
        contents = new byte[(int) file.length()];
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
        is.read(contents);
        return contents;
    }

    @Override
    public void write(String filename, byte[] contents) throws RemoteException, FileNotFoundException,IOException {
        File file = new File(getStorageLocation()+"/"+filename);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        os.write(contents);
        os.flush();
    }


}
