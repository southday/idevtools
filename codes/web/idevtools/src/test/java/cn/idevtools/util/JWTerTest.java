package cn.idevtools.util;

import cn.idevtools.common.CommonConst;
import org.junit.Test;

/**
 * @author southday
 * @date 2019/3/6
 */
public class JWTerTest {

    @Test
    public void createTokenTest() {
        String jws = JWTer.createLoginedToken(75, "uuu", CommonConst.USER_TYPE_USER);
//        String jws = JWTer.createLoginedToken(1, "southday", CommonConst.USER_TYPE_ADMIN);
        System.out.println(jws);
    }
}
