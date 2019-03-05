package cn.idevtools.util;

import cn.idevtools.common.CommonConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
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
 * @date   2019/3/5
 */
public class JWTer {
    private static final Logger logger = LogManager.getLogger(JWTer.class);
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
     * 从request的header中获取token southday 2019.03.05
     * @return
     */
    public static String getToken() {
        return HttpUtil.getHttpServletRequest().getHeader(CommonConst.TOKEN);
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
        Map<String, Object> claims = new HashMap<>();
        claims.put(CommonConst.ID, id);
        claims.put(CommonConst.USER_NAME, userName);
        claims.put(CommonConst.USER_TYPE, userType);
        String jws = Jwts.builder().setClaims(claims).setNotBefore(startDate).setExpiration(expireDate).signWith(KEY).compact();
        return jws;
    }

    /**
     * 使登陆token无效化 southday 2019.03.02
     * @param jws 原token
     * @return 无效化后的token
     */
    public static String disabledLoginedToken(String jws) {
        Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jws).getBody();
        claims.setExpiration(new Date());
        return Jwts.builder().setClaims(claims).signWith(KEY).compact();
    }

    /**
     * 用户登陆成功后，在cookie中添加token southday 2019.02.28
     * @param id
     * @param userName
     * @param userType
     * @return
     */
    public static boolean addLoginedToken(int id, String userName, String userType) {
        try {
            HttpServletResponse resp = HttpUtil.getHttpServletResponse();
            String jws = JWTer.createLoginedToken(id, userName, userType);
            resp.setHeader(CommonConst.TOKEN, jws);
            resp.setHeader("Access-Control-Expose-Headers", CommonConst.TOKEN);
        } catch (Exception e) {
           logger.error("addLoginedToken-createLoginedToken error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 用户退出登录，使token无效化 southday 2019.03.05
     * @return
     */
    public static boolean disableLoginedToken() {
        try {
            HttpServletResponse resp = HttpUtil.getHttpServletResponse();
            String jws = JWTer.disabledLoginedToken(getToken());
            resp.setHeader(CommonConst.TOKEN, jws);
            resp.setHeader("Access-Control-Expose-Headers", CommonConst.TOKEN);
        } catch (Exception e) {
            logger.error("disableLoginedToken-disabledLoginedToken error: " + e.getMessage());
            return false;
        }
        return true;
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

    public static int getId(String jws) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jws).getBody().get(CommonConst.ID, Integer.class);
    }

    public static String getUserName(String jws) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jws).getBody().get(CommonConst.USER_NAME, String.class);
    }

    public static String getUserType(String jws) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jws).getBody().get(CommonConst.USER_TYPE, String.class);
    }
}
