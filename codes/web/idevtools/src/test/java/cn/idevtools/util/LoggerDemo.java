package cn.idevtools.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author southday
 * @date 2019/2/26
 */
public class LoggerDemo {
//    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    private static final Logger logger = LogManager.getLogger(LoggerDemo.class);

    public static void main(String[] args) {
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        logger.trace("trace");
        logger.fatal("fatal");
        logger.warn("warn");
    }
}
