package cn.idevtools.controller;

import cn.idevtools.common.CodeMsg;
import cn.idevtools.common.CommonConst;
import cn.idevtools.common.Message;
import cn.idevtools.po.AdminT;
import cn.idevtools.util.JWTUtil;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.idevtools.service.AdminService;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public Message<AdminT> login(AdminT argAdmin, HttpServletRequest req, HttpServletResponse resp) {
        String captchaResp = req.getParameter("jcaptcha");
        System.out.println("验证码：" + captchaResp);
        boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(req, captchaResp);
        Message<AdminT> ret = new Message<>();
        if (!captchaPassed) {
            ret.setCodeMsg(CodeMsg.CAPTCHA_ERROR);
            return ret;
        }
        AdminT admin = adminService.login(argAdmin);
        if (admin == null) {
            ret.setCodeMsg(CodeMsg.LOGIN_FAILURE_INPUT_ERROR);
        } else {
            ret.setCodeMsg((CodeMsg.LOGIN_SUCCESS));
            ret.setData(admin);
            try {
                String token = JWTUtil.createToken(admin.getAdminName(), CommonConst.USER_TYPE_ADMIN);
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                resp.addCookie(cookie);
            } catch (Exception e) {
                ret.setCodeMsg(CodeMsg.LOGIN_FAILURE_TOKEN_ERROR);
                e.printStackTrace();
                logger.error("Create token error, [adminName: " + admin.getAdminName() + "]，exception: " + e.getMessage());
            }
        }
        return ret;
    }
}
