import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterService{
    void bind(String name, StorageService service) throws AlreadyBoundException, MalformedURLException, RemoteException;

    void unbind(String name, StorageService service) throws NotBoundException, MalformedURLException, RemoteException;

}
