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
        /*
        RMI原理：
        1.服务器创建好继承于Remote接口的类，并把它绑定到RMI服务器上
        2.客户端请求RMI服务器上的类
        3.服务端返回客户端所请求类的存根stub，客户端将这个stub看作实例化对象使用
        4.客户端调用stub的某个方法，并传入参数。该参数会发送到RMI服务器上，由RMI服务器按照客户端传来的参数来执行指定的方法
        5.服务器执行完后将结果返回给客户端
         */

        Registry registry = LocateRegistry.getRegistry("localhost",1099);
        //这里要转化为接口类，如果转化为实现类会发现报错
        RemoteInterface rmiObj = (RemoteInterface) registry.lookup("RMIObj");
        rmiObj.sayHello("hi");
    }
}
