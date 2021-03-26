import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RegisterServiceImpl implements RegisterService{
    @Override
    public void bind(String name, StorageService service) throws AlreadyBoundException, MalformedURLException, RemoteException {
        Naming.bind("rmi://localhost:6789/"+name,service);
    }

    @Override
    public void unbind(String name) throws NotBoundException, MalformedURLException, RemoteException {
        Naming.unbind("rmi://localhost:6789/"+name);
    }
}
