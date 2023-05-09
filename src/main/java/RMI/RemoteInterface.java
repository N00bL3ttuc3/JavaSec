package RMI;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/3/31 19:41
 */
public interface RemoteInterface extends Remote {
    String sayHello(String words) throws IOException;
    void evalCode(Object object) throws RemoteException;
}
