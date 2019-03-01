package cn.idevtools.util;

import cn.idevtools.common.CommonConst;
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
 * <blockquote><pre>
 *     <a href="https://blog.csdn.net/a1203177935/article/details/80875562">https://blog.csdn.net/a1203177935/article/details/80875562"</a>
 *     <a href="https://github.com/jwtk/jjwt">https://github.com/jwtk/jjwt</a>
 * </pre></blockquote>
 * @author southday
 * @date   2019/2/26
 */
public class JWTer {
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int DURATION = 7 * 24; // 默认token有效时长有7天
    private int id;
    private String userName;
    private String userType;
    private Claims claims;

    public JWTer(String jws) {
        claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jws).getBody();
        id = claims.get(CommonConst.ID, Integer.class);
        userName = claims.get(CommonConst.USER_NAME, String.class);
        userType = claims.get(CommonConst.USER_TYPE, String.class);
    }

    /**
     * 创建登陆token southday 2019.03.01
     * @param id
     * @param userName
     * @param userType
     * @return
     */
    public static String createLoginedToken(int id, String userName, String userType) {
        Date startDate = new Date();
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, DURATION);
        Date expireDate = now.getTime();
        Map<String, Object> claims = argClaims(id, userName, userType);
        String jws = Jwts.builder().setClaims(claims).setNotBefore(startDate).setExpiration(expireDate).signWith(KEY).compact();
        return jws;
    }

    /**
     * 生成无效的token southday 2019.03.01
     * @return
     */
    public static String disabledLoginedToken() {
        JWTer jwter = new JWTer(CookieUtil.getCookieValue(CommonConst.TOKEN));
        return disabledLoginedToken(jwter.getId(), jwter.getUserName(), jwter.getUserType());
    }

    /**
     * 生成无效的登陆token southday 2019.03.01
     * @param id
     * @param userName
     * @param userType
     * @return
     */
    public static String disabledLoginedToken(int id, String userName, String userType) {
        Date startDate = new Date();
        Map<String, Object> claims = argClaims(id, userName, userType);
        String jws = Jwts.builder().setClaims(claims).setNotBefore(startDate).setExpiration(startDate).signWith(KEY).compact();
        return jws;
    }

    private static Map<String, Object> argClaims(int id, String userName, String userType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CommonConst.ID, id);
        claims.put(CommonConst.USER_NAME, userName);
        claims.put(CommonConst.USER_TYPE, userType);
        return claims;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public int getId() {
        return id;
    }
}
