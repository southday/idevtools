package cn.idevtools.util;

import org.junit.Test;

/**
 * @author southday
 * @date 2019/2/27
 */
public class EncryptUtilTest {

    @Test
    public void foo() {
        String pass = "123";
        String md5pass = EncryptUtil.md5salt(pass);
        System.out.println(md5pass);
    }
}
