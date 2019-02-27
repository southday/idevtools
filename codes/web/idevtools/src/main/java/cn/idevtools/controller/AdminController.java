package cn.idevtools.controller;

import cn.idevtools.common.CodeMsg;
import cn.idevtools.common.CommonConst;
import cn.idevtools.common.Message;
import cn.idevtools.po.AdminT;
import cn.idevtools.service.AdminService;
import cn.idevtools.service.CommonService;
import cn.idevtools.util.EncryptUtil;
import cn.idevtools.util.JWTUtil;
import cn.idevtools.util.ParamValidator;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    AdminService adminService;

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseJSONP
    public Message<AdminT> login(AdminT argAdmin, HttpServletRequest req, HttpServletResponse resp) {
        if (!commonService.checkCaptcha(req))
            return new Message<>(CodeMsg.CAPTCHA_ERROR);
        argAdmin.setPassword(EncryptUtil.md5salt(argAdmin.getPassword()));
        AdminT admin = adminService.login(argAdmin);
        Message<AdminT> ret = new Message<>(CodeMsg.LOGIN_SUCCESS, admin);
        if (admin == null) {
            ret.setCodeMsg(CodeMsg.LOGIN_FAILURE_INPUT_ERROR);
        } else {
            try {
                String token = JWTUtil.createToken(admin.getAdminName(), CommonConst.USER_TYPE_ADMIN);
                Cookie cookie = new Cookie(CommonConst.TOKEN, token);
                cookie.setPath("/");
                resp.addCookie(cookie);
            } catch (Exception e) {
                e.printStackTrace();
                ret.setCodeMsg(CodeMsg.LOGIN_FAILURE_TOKEN_ERROR);
            }
        }
        return ret;
    }
}
