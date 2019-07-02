package cn.idevtools.util;

import cn.idevtools.common.CommonConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.spec.SecretKeySpec;
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
    private static final String strKey = "sky(*&#@87874KQER!@@)(#SDGsdf2pq*#@WE23S";
    private static final Key KEY = new SecretKeySpec(strKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    private static final int DURATION = 7 * 24; // 默认token有效时长有7天
    private int id;
    private String userName;
    private String userType;
    /* 是否可用，当new JWTer(jws)发生异常时，new出的对象不可用
     * 在调用getUserName(), getId(), getUserType()方法前，要先调用isUsable()方法判断是否可以用
     */
    private boolean usable = true;
    private Exception exception; // 外部程序可能需要这个异常

    public JWTer(String jws) {
        try {
            Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jws).getBody();
            id = claims.get(CommonConst.ID, Integer.class);
            userName = claims.get(CommonConst.USER_NAME, String.class);
            userType = claims.get(CommonConst.USER_TYPE, String.class);
        } catch (Exception e) {
            logger.debug("new JWTer(jws) error: " + e.getMessage() + "| [token = " + jws + "]");
            usable = false;
            exception = e;
        }
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
     * @param id 用户或管理员ID
     * @param userName 用户或管理员名称
     * @param userType 用户/管理员
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
            logger.info("addLoginedToken-createLoginedToken error: " + e.getMessage());
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
            logger.info("disableLoginedToken-disabledLoginedToken error: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 调用前先调用isUsable()方法判断对象是否可用
     * southday 2019.03.06
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 调用前先调用isUsable()方法判断对象是否可用
     * southday 2019.03.06
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 调用前先调用isUsable()方法判断对象是否可用
     * southday 2019.03.06
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 标识：new JWTer(jws)生成的对象是否可用
     * southday 2019.03.06
     * @return
     */
    public boolean isUsable() {
        return usable;
    }

    /**
     * 返回new JWTer(jws)时产生的异常
     * @return
     */
    public Exception getException() {
        return exception;
    }
}
