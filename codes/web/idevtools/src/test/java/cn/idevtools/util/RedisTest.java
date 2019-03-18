package cn.idevtools.util;

import cn.idevtools.CommonTest;
import cn.idevtools.po.UserT;
import cn.idevtools.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 测试redis
 * @author 王沁宽
 * @date 2019/3/18
 */
public class RedisTest extends CommonTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserServiceImpl userService;

    @Test
    public void redisTemplateTest(){
        userService.getUserByUserId(2);
        System.out.println(redisTemplate.hasKey("user::2"));
        UserT u = (UserT)redisTemplate.opsForValue().get("user::2");
        System.out.println(u.getUserName());

    }
}
