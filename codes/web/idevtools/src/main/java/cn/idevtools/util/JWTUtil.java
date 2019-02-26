package cn.idevtools.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT权限验证工具<br>
 * 参考网址：
 * <blockquto><pre>
 *     <a href="https://blog.csdn.net/a1203177935/article/details/80875562">https://blog.csdn.net/a1203177935/article/details/80875562"</a>
 *     <a href="https://github.com/jwtk/jjwt">https://github.com/jwtk/jjwt</a>
 * </pre></blockquto>
 * @author southday
 * @date   2019/2/26
 */
public class JWTUtil {
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int DURATION = 7 * 24; // 默认token有效时长有7天

    /**
     * 根据userName创建token
     * @param userName
     * @return
     */
    public static String createToken(String userName, String userType) {
        Date startDate = new Date();
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, DURATION);
        Date expireDate = now.getTime();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", userName);
        claims.put("userType", userType);
        String jws = Jwts.builder()
                .setClaims(claims)
                .setNotBefore(startDate)
                .setExpiration(expireDate)
                .signWith(KEY)
                .compact();
        return jws;
    }

    /**
     * 获取claims(主要获取：userName，userType)
     * @param jws
     * @return
     */
    public static Claims getClaims(String jws) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jws).getBody();
    }
}
