package JNDI.Client;

import javax.naming.InitialContext;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/11 15:43
 */
public class Client2 {
    public static void main(String[] args) throws Exception{
        String url = "rmi://localhost:1099/obj";
        InitialContext initialContext = new InitialContext();
        initialContext.lookup(url);
    }
}
