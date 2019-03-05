package cn.idevtools.controller;

import cn.idevtools.common.CommonConst;
import cn.idevtools.common.Message;
import cn.idevtools.common.StatusCode;
import cn.idevtools.po.UserT;
import cn.idevtools.service.UserService;
import cn.idevtools.util.JWTer;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试使用的Controller<br/>
 * 重写JWT部分，将token存在header中返回，使用header提交验证
 * @author southday
 * @date 2019/3/5
 */
@Controller
@RequestMapping("/test")
public class JWTHeaderTestController {
    @Autowired
    private UserService userService;

    @ResponseJSONP
    @RequestMapping(value = "/u/{userId}", method = RequestMethod.GET)
    public Message<?> getUser(@PathVariable int userId) {
        UserT user = userService.getUserByUserId(userId);
        if (user == null)
            return new Message<>(StatusCode.FAILURE, "获取用户信息失败");
        boolean success = JWTer.addLoginedToken(user.getUserId(), user.getUserName(), CommonConst.USER_TYPE_USER);
        if (success)
            System.out.println("add logined token succeed");
        return new Message<>(StatusCode.SUCCESS, "获取用户信息成功", user);
    }

    @ResponseJSONP
    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public Message<?> getToken(HttpServletRequest req) {
        String jws = req.getHeader(CommonConst.TOKEN);
        return (jws == null || jws.trim().length() == 0) ?
                new Message<>(StatusCode.FAILURE, "获取的token为空字符串") :
                new Message<>(StatusCode.SUCCESS, "获取token成功", jws);
    }
}
