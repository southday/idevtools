package cn.idevtools.interceptor;

import cn.idevtools.common.CommonConst;
import cn.idevtools.service.AdminService;
import cn.idevtools.service.UserService;
import cn.idevtools.util.CookieUtil;
import cn.idevtools.util.JWTer;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * JWT验证拦截器，对于需要身份认证的请求，必须先经过该拦截器处理
 * @author southday
 * @date 2019/2/26
 */
public class JWTVerifyInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(JWTVerifyInterceptor.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String jws = JWTer.getToken();
        JWTer jwter = new JWTer(jws);
        boolean flag = false;
        if (!jwter.isUsable()) {
            logger.info("权限验证失败，[token = " + jws + "，异常：" + jwter.getException().getMessage());
        } else {
            String userName = jwter.getUserName();
            String userType = jwter.getUserType();
            if (CommonConst.USER_TYPE_ADMIN.equals(userType))
                flag = adminService.isAdminExists(userName);
            else if (CommonConst.USER_TYPE_USER.equals(userType))
                flag = userService.isUserExists(userName);
        }
        if (!flag)
            resp.setStatus(401);
        return flag;
    }
}
