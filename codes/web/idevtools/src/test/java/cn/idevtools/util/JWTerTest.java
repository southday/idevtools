package cn.idevtools.util;

import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @author southday
 * @date 2019/2/26
 */
public class JWTerTest {

    @Test
    public void foo() {
        String jws = JWTer.createLoginedToken(123,"southday", "user");
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
