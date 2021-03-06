package RMI_FileTransfer;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileServiceImpl extends UnicastRemoteObject implements FileService{

    public FileServiceImpl() throws RemoteException {
    }

    @Override
    public void upload(String filename, byte[] fileContent) throws RemoteException {
        File file = new File(filename);
        try {
            if (!file.exists()) file.createNewFile();
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            os.write(fileContent);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
