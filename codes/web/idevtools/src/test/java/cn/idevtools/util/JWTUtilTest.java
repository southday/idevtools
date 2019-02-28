package cn.idevtools.util;

import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @author southday
 * @date 2019/2/26
 */
class JWTUtilTest {

    @Test
    public void foo() {
        String jws = JWTUtil.createToken(123,"southday", "user");
        System.out.println(jws);
        Claims claims = JWTUtil.getClaims(jws);
        String userName = claims.get("userName", String.class);
        String userType = claims.get("userType", String.class);
        System.out.println(userName + ", " + userType);
    }
}
