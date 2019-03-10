package cn.idevtools.util;

import org.junit.Test;

/**
 * @author 王沁宽
 * @date 2019/3/10
 */
public class DESCipherTest {
    @Test
    public void testDESCipher() throws  Exception{
        DESCipher desCipher = DESCipher.getInstance();
        String encrypt = "";
        System.out.println(encrypt = desCipher.encrypt("84"));
        System.out.println(desCipher.decrypt(encrypt));
    }
}
