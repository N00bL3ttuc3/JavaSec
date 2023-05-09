package RMI.Client;

import RMI.RemoteImpl;
import RMI.RemoteInterface;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/9 14:49
 */
public class Client {
    public static void main(String[] args) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost",1099);
        //这里要转化为接口类，如果转化为实现类会发现报错
        RemoteInterface rmiObj = (RemoteInterface) registry.lookup("RMIObj");
        rmiObj.sayHello("hi");
    }
}
