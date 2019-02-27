package cn.idevtools.common;

import cn.idevtools.po.UserT;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一封装返回msg测试
 * @author southday
 * @date 2019/2/26
 */
public class MessageTest {

    @Test
    public void singleObjectTest() {
        UserT user = new UserT();
        user.setUserId(1);
        user.setPassword("sss");
        user.setEmail("lll@qq.com");
        Message<UserT> ret = new Message<>(CodeMsg.CAPTCHA_ERROR, user);
        String json = JSON.toJSONString(ret);
        System.out.println(json);
    }

    @Test
    public void ListObjectTest() {
        List<UserT> users = new ArrayList<>();

        UserT user1 = new UserT();
        user1.setUserId(1);
        user1.setPassword("sss");
        user1.setEmail("lll@qq.com");

        UserT user2 = new UserT();
        user2.setUserId(2);
        user2.setPassword("smfsd");

        users.add(user1);
        users.add(user2);

        Message<List<UserT>> ret = new Message<>(-2, "程序异常", users);
        String json = JSON.toJSONString(ret);
        System.out.println(json);
    }
}
