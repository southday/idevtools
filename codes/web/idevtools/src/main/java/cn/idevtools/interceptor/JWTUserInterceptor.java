package cn.idevtools.interceptor;

import cn.idevtools.common.CommonConst;
import cn.idevtools.service.AdminService;
import cn.idevtools.service.UserService;
import cn.idevtools.util.JWTer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * JWT验证拦截器(用户)，对于需要身份认证的请求，必须先经过该拦截器处理
 * @author southday
 * @date 2019/2/26
 */
public class JWTUserInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(JWTUserInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String jws = JWTer.getToken();
        JWTer jwter = new JWTer(jws);
        boolean flag = false;
        if (!jwter.isUsable()) {
            logger.info("权限验证失败，异常：" + jwter.getException().getMessage() + " | [token = " + jws + "]");
        } else if (!CommonConst.USER_TYPE_USER.equals(jwter.getUserType())) {
            logger.info("权限验证失败，用户类型不匹配，[token = " + jws + "]");
        } else {
            flag = userService.isUserExists(jwter.getUserName());
        }
        if (!flag) {
            resp.setStatus(401);
//            resp.sendRedirect("/idevtools/search"); // 本地使用
            resp.sendRedirect("/search"); // 远程使用
        }
        return flag;
    }
}
