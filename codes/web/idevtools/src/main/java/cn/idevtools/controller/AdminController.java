package cn.idevtools.controller;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.CommonConst;
import cn.idevtools.common.Message;
import cn.idevtools.po.AdminT;
import cn.idevtools.service.AdminService;
import cn.idevtools.util.CookieUtil;
import cn.idevtools.util.EncryptUtil;
import cn.idevtools.util.ValidUtil;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @ResponseJSONP
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Message<?> login(@Valid AdminT argAdmin, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        if (!ValidUtil.isPassCaptcha())
            return new Message<>(CodeMsgE.CAPTCHA_ERROR);
        argAdmin.setPassword(EncryptUtil.md5salt(argAdmin.getPassword()));
        AdminT admin = adminService.login(argAdmin);
        Message<AdminT> ret = new Message<>(CodeMsgE.LOGIN_SUCCESS, admin);
        if (admin == null) {
            ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_INPUT_ERROR);
        } else {
            boolean success = CookieUtil.addLoginedToken(admin.getAdminId(), admin.getAdminName(), CommonConst.USER_TYPE_ADMIN);
            if (!success)
                ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_TOKEN_ERROR);
        }
        return ret;
    }

    @ResponseJSONP
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Message<?> logout() {
        return adminService.logout();
    }
}
