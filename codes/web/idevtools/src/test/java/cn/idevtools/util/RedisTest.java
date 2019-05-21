package cn.idevtools.util;

import cn.idevtools.CommonTest;
import cn.idevtools.redis.Recommend;
import cn.idevtools.redis.RecommendThread;
import cn.idevtools.redis.RedisUtil;
import cn.idevtools.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 测试redis
 * 如果报错，注释掉{@link cn.idevtools.redis.Recommend} 中获取路径的代码
 * @author 王沁宽
 * @date 2019/3/18
 *
 */
public class RedisTest extends CommonTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RecommendThread r;

    @Test
    public void redisTemplateTest() throws Exception {
//        userService.getUserByUserId(2);
//        System.out.println(redisTemplate.hasKey("user::2"));
//        UserT u = (UserT)redisTemplate.opsForValue().get("user::2");
//        System.out.println(u.getUserName());

        //RedisUtil a = new RedisUtil();
//        redisUtil.LikeTool(2,2);
//        redisUtil.LikeTool(123,312);
//        redisUtil.LikeTool(123,312);
//        redisUtil.LikeTool(123,212,10);
        //System.out.println(redisTemplate.opsForValue().increment("222::212"));
       // r.run();
        generateData();
    }

    /**
     * 生成10万条测试数据
     */
    public void generateData(){
        Random random = new Random();
        int uid,toolid;
        for(int i = 0;i < 100000;i++){
            uid = random.nextInt(500) ;
            toolid = random.nextInt(500);
            redisUtil.LikeTool(uid,toolid);
        }

    }
}
