package cn.idevtools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * 提供log支持
 * @author southday
 * @date   2019/2/24
 */
public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {
    static {
        try {
            String sep = System.getProperty("file.separator");
            String path = System.getProperty("user.dir") + "$src$main$resources$config$log4j2.xml";
            path = path.replace("$", sep);
            File log4j2File = new File(path);
            LoggerContext logContext = (LoggerContext) LogManager.getContext(false);
            logContext.setConfigLocation(log4j2File.toURI());
            logContext.reconfigure();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JUnit4ClassRunner(Class<?> clazz) throws InitializationError{
        super(clazz);
    }
}
