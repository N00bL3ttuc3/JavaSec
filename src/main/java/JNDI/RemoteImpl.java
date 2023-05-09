package JNDI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/9 15:14
 */
public class RemoteImpl extends UnicastRemoteObject implements RemoteInterface {

    public RemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String words) throws RemoteException {
        System.out.println("JNDI hello");
        return words;
    }
}
