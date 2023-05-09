package RMI.Server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/3/31 19:41
 */
public class RemoteImpl extends UnicastRemoteObject implements RemoteInterface {

    protected RemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String words) throws IOException {
        System.out.println("hello world!");
        Runtime.getRuntime().exec("open -a Calculator.app");
        return words;
    }

    @Override
    public void evalCode(Object object) throws RemoteException {
        System.out.println("eval code");
    }
}
