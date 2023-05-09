package JNDI.Client;

import JNDI.RemoteInterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/9 15:20
 */
public class Client {
    /*
    在实际操作的过程中，发现了如果Server端使用的默认配置，我们不需要额外的设置环境，直接Context context = new InitialContext();就可以进行操作
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, NamingException {

        //比RMI多的设置环境
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL,"rmi://localhost:1023");

        Context context = new InitialContext(env);
        RemoteInterface rmiObj = (RemoteInterface) context.lookup("RMIObj");
        rmiObj.sayHello("hello");
    }
}
