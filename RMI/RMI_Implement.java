package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI_Implement extends UnicastRemoteObject implements RMI_Interface {
    private RMIFile file;
    public RMI_Implement() throws RemoteException {
        super();
    }

    public void setFile(RMIFile file) {
        this.file = file;
    }

    public RMIFile getFile() {
        return file;
    }
}
