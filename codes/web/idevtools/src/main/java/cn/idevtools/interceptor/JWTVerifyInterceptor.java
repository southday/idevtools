package cn.idevtools.interceptor;

import cn.idevtools.common.CommonConst;
import cn.idevtools.service.AdminService;
import cn.idevtools.service.UserService;
import cn.idevtools.util.CookieUtil;
import cn.idevtools.util.JWTer;
import io.jsonwebtoken.Claims;
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
        String jws = CookieUtil.getCookieValue(req, CommonConst.TOKEN);
        boolean flag = false;
        if (jws != null && !"".equals(jws.trim())) {
            try {
                JWTer jwter = new JWTer(jws);
                String userName = jwter.getUserName();
                String userType = jwter.getUserType();
                if (CommonConst.USER_TYPE_ADMIN.equals(userType))
                    flag = adminService.isAdminExists(userName);
                else if (CommonConst.USER_TYPE_USER.equals(userType))
                    flag = userService.isUserExists(userName);
            } catch (RuntimeException e) {
                e.printStackTrace();
                logger.error("权限验证失败，[token=" + jws + "]，异常：" + e.getMessage());
            }
        }
        if (!flag)
            resp.setStatus(401);
        return flag;
    }
}
