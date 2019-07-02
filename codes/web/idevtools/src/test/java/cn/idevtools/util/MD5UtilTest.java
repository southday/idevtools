package cn.idevtools.util;

import org.junit.Test;

/**
 * @author southday
 * @date 2019/2/27
 */
public class MD5UtilTest {

    @Test
    public void foo() {
        String pass = "123";
        String md5pass = MD5Util.md5salt(pass);
        System.out.println(md5pass);
    }
}
