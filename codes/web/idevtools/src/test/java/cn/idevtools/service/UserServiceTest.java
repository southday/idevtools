package cn.idevtools.service;

import cn.idevtools.CommonTest;
import cn.idevtools.po.UserT;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author southday
 * @date 2019/3/4
 */
public class UserServiceTest extends CommonTest {
    @Autowired
    UserService userService;

    @Test
    public void getUserInfoTest() {
        UserT user = userService.getUserByUserId(67);
        System.out.println(JSON.toJSON(user));

        UserT user2 = userService.getUserByUserId(-1);
        System.out.println("user2 = " + user2);
    }
}
