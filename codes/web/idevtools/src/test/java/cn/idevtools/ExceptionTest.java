package cn.idevtools;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.SchemaOutputResolver;

/**
 * 异常处理Test
 * @author southday
 * @date 2019/3/5
 */
public class ExceptionTest {
    static final Logger logger = LogManager.getLogger(ExceptionTest.class);

    public static void foo() throws Exception {
        throw new NullPointerException("空指针");
    }

    public static void soo() {
        try {
            foo();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("空指针异常：\n" + ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        soo();
    }

    // output: catch null pointer exception
}
