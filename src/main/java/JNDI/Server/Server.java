package JNDI.Server;

import JNDI.RemoteImpl;
import JNDI.RemoteInterface;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;


/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/9 15:15
 */
public class Server {
    /*
    最简单的可以这样理解：我们依旧是通过createRegistry(port)创建了RMI注册中心
    但是我们之后不实用这个registry来bind，而是通过JNDI设置一个上下文环境，用这个Context来代替注册中心管理RMI
    背后的原理就是JDNI有多个SPI实现，这个Context可以通过设置不同的环境管理不同的服务，做到了大一统
    Client端也需要设置环境才可以通过Context访问
     */
    public static void main(String[] args) throws NamingException, RemoteException, AlreadyBoundException {
        LocateRegistry.createRegistry(1023);

        //比RMI多了设置环境的步骤
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL,"rmi://localhost:1023");
        Context context = new InitialContext(env);

        RemoteInterface remoteObj = new RemoteImpl();
        context.bind("RMIObj",remoteObj);

    }
}
