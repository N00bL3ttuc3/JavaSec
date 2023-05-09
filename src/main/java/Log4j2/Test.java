package Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.function.LongFunction;
/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/6 19:27
 */

public class Test {
    public static void main( String[] args )
    {
        Logger logger = LogManager.getLogger(LongFunction.class);
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");
    }
}