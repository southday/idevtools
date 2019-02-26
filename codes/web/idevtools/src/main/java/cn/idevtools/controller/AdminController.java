package cn.idevtools.controller;

import cn.idevtools.common.CommonConst;
import cn.idevtools.po.AdminT;
import cn.idevtools.util.JWTUtil;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.idevtools.service.AdminService;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员相关业务模块 Controller
 * @author southday
 * @date 2019/2/26
 * @see AdminService
 */
@Controller
@RequestMapping("/a")
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseJSONP
    public AdminT login(AdminT argAdmin, HttpServletResponse resp) {
        AdminT admin = adminService.login(argAdmin);
        if (admin != null) {
            try {
                String token = JWTUtil.createToken(admin.getAdminName(), CommonConst.USER_TYPE_ADMIN);
                System.out.println("token: " + token);
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                resp.addCookie(cookie);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Create token error, [adminName: " + admin.getAdminName() + "]，exception: " + e.getMessage());
                admin = null;
            }
        }
        return admin;
    }
}
