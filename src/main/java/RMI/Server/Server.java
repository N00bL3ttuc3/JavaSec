package RMI.Server;

import RMI.RemoteImpl;
import RMI.RemoteInterface;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/3/31 19:41
 */
public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        RemoteImpl remoteObj = new RemoteImpl();
        LocateRegistry.createRegistry(1099);
        Naming.bind("rmi://localhost:1099/RMIObj",remoteObj);
    }
}
