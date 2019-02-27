package cn.idevtools.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 一些网络处理的工具
 * @author 王沁宽
 */
public class HttpUtil {
    /**
     * 获取HttpServletRequest
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 通过cookie名称获取对应cookie
     * @param cookieName
     * @return
     */
    public static Cookie getCookieByName(String cookieName){
        Cookie cookie=null;
        Cookie[] cookies = getHttpServletRequest().getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie=c;
                    break;
                }
            }
        }
        return cookie;
    }
}
