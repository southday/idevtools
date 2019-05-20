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
//        userService.getUserByUserId(2);
//        System.out.println(redisTemplate.hasKey("user::2"));
//        UserT u = (UserT)redisTemplate.opsForValue().get("user::2");
//        System.out.println(u.getUserName());
        String key = "123::123";
        Integer num ;
        if((num = (Integer)redisTemplate.opsForValue().get(key))!= null){
            System.out.print(num);
            redisTemplate.opsForValue().increment(key,100);
        } else {
            redisTemplate.opsForValue().set(key,1);
        }

    }
}
