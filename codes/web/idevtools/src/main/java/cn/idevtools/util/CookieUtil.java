package cn.idevtools.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie处理工具类
 * @author southday
 * @date 2019/2/27
 */
public class CookieUtil {

    /**
     * 根据cookieName获取指定cookie
     * @param req
     * @param cookieName
     * @return
     */
    public static Cookie getCookie(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0)
            for (Cookie cookie : req.getCookies())
                if (cookieName.equals(cookie.getName()))
                    return cookie;
        return null;
    }

    /**
     * 根据cookieName获取指定cookie的value
     * @param req
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest req, String cookieName) {
        Cookie cookie = getCookie(req, cookieName);
        return cookie != null ? cookie.getValue() : null;
    }
}
