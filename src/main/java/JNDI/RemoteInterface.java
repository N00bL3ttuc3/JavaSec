package JNDI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/9 15:13
 */
public interface RemoteInterface extends Remote {
    public String sayHello(String words) throws RemoteException;
}
