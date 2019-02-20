package cn.idevtools.controller;


import cn.idevtools.mapper.UserTMapper;
import cn.idevtools.po.UserT;
import cn.idevtools.service.UserService;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 用于进行一些临时的测试，如表单的提交处理状况等,无实际用途
 * 测试对象:userTMapper
 *
 * @author 王沁宽
 */
@RequestMapping("/test")
@Controller
public class TestController {
    @Autowired
    UserService userService;
    @Autowired
    UserTMapper userTMapper;

    /**
     * 测试查询用户信息
     *
     */
    @ResponseJSONP
    @RequestMapping("/selectuser")
    public List<UserT> testMapper(){
        UserT user=new UserT();
        user.setValid((byte)0);
        return userService.getUsersPage(user,0,10).getList();
    }
}
