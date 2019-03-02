package cn.idevtools.util;

import cn.idevtools.common.CommonConst;
import cn.idevtools.common.annotation.PrintExecTime;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @author southday
 * @date 2019/2/26
 */
public class JWTerTest {

    @Test
    public void foo() {
        String jws = JWTer.createLoginedToken(123,"southday", CommonConst.USER_TYPE_ADMIN);
        System.out.println(jws);
        JWTer jwter = new JWTer(jws);
        String userName = jwter.getUserName();
        String userType = jwter.getUserType();
        System.out.println(userName + ", " + userType);
    }

    @Test
    public void unUseTest() {
        String jws = JWTer.disabledLoginedToken(123, "southday", "user");
        JWTer jwter = new JWTer(jws);
        String userName = jwter.getUserName();
        String userType = jwter.getUserType();
        System.out.println(userName + ", " + userType);
    }
}
