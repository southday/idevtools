package cn.idevtools.controller;


import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.CommonConst;
import cn.idevtools.common.Message;
import cn.idevtools.common.StatusCode;
import cn.idevtools.po.UserT;
import cn.idevtools.service.EmailService;
import cn.idevtools.service.UserService;
import cn.idevtools.util.EncryptUtil;
import cn.idevtools.util.JWTer;
import cn.idevtools.util.ValidUtil;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 该类用于实现管理员用户管理模块.
 * 主要用于管理员用户的增删改查
 * @see UserService
 *
 * @author 王沁宽
 * @date 2019/2/27
 */
@Controller
@RequestMapping("/u")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    /**
     * 用户登陆 southday 2019.02.28
     * @param argUser
     * @param bindingResult
     * @return
     */
    @ResponseJSONP
    @PostMapping("/login")
    public Message<?> login(@Valid UserT argUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        if (!ValidUtil.isPassCaptcha())
            return new Message<>(CodeMsgE.CAPTCHA_ERROR);
        argUser.setPassword(EncryptUtil.md5salt(argUser.getPassword()));
        UserT user = userService.login(argUser);
        Message<UserT> ret = new Message<>(CodeMsgE.LOGIN_SUCCESS, user);
        if (user == null) {
            ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_INPUT_ERROR);
        } else {
            boolean success = JWTer.addLoginedToken(user.getUserId(), user.getUserName(), CommonConst.USER_TYPE_USER);
            if (!success)
                ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_TOKEN_ERROR);
        }
        return ret;
    }

    /**
     * 用户注册 southday 2019.02.28
     * @param argUser
     * @param bindingResult
     * @return
     */
    @ResponseJSONP
    @PostMapping("/join")
    public Message<?> join(@Valid UserT argUser, BindingResult bindingResult, HttpServletRequest req) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        if (!ValidUtil.isPassCaptcha(req))
            return new Message<>(CodeMsgE.CAPTCHA_ERROR);
        String password2 = req.getParameter("password2");
        if (!argUser.getPassword().equals(password2))
            return new Message<>(StatusCode.FAILURE, "注册失败，两次密码不一致");
        boolean userNameExists = userService.isUserNameExists(argUser.getUserName());
        boolean emailExists = userService.isEmailExists(argUser.getEmail());
        String msg = null;
        if (userNameExists && !emailExists)
            msg = "注册失败，用户名已被注册";
        else if (!userNameExists && emailExists)
            msg = "注册失败，邮箱已被注册";
        else if (userNameExists && emailExists)
            msg = "注册失败，用户名和邮箱均已被注册";
        if (msg != null) {
            return new Message<>(StatusCode.FAILURE, msg);
        } else {
            argUser.setPassword(EncryptUtil.md5salt(argUser.getPassword()));
            boolean joinSuccess = userService.join(argUser);
            if (!joinSuccess) {
                return new Message<>(StatusCode.FAILURE, "注册失败，请稍后重试");
            } else {
                argUser.setPassword(null);
                boolean addTokenSuccess = JWTer.addLoginedToken(argUser.getUserId(), argUser.getUserName(), CommonConst.USER_TYPE_USER);
                //发送验证邮件
                emailService.sendValidEmail(argUser);
                msg = addTokenSuccess ? "注册成功" : "注册成功，Token创建异常";
                return new Message<>(StatusCode.SUCCESS, msg, argUser);
            }
        }
    }

    /**
     * 用户退出登录
     * southday 2019.03.05
     * @return
     */
    @ResponseJSONP
    @PostMapping("/logout")
    public Message<?> logout() {
        return userService.logout();
    }

    /**
     * 根据token获取用户信息
     * southday 2019.03.05
     * @return
     */
    @ResponseJSONP
    @GetMapping("/userInfo")
    public Message<?> getUserInfo() {
        JWTer jwter = new JWTer(JWTer.getToken());
        if (!jwter.isUsable())
            return new Message<>(StatusCode.FAILURE, "获取用户信息失败");
        UserT user = userService.getUserByUserId(jwter.getId());
        return user != null ?
                new Message<>(StatusCode.SUCCESS, "获取用户信息成功", user) :
                new Message<>(StatusCode.FAILURE, "获取用户信息失败");
    }

    /**
     * 激活用户
     * 王沁宽 2019.03.09
     */
    @ResponseJSONP
    @GetMapping("/active/{userId}")
    public Message<?> activeUser(@PathVariable Integer userId){

        emailService.sendValidEmail(userService.getUserByUserId(userId));

        return userService.activeUser(userId) ?
                new Message<>(StatusCode.SUCCESS,"激活成功") :
                new Message<>(StatusCode.FAILURE,"激活失败");
    }
}
