package cn.idevtools.service;

import cn.idevtools.CommonTest;
import cn.idevtools.po.UserT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 邮件服务测试
 * @author 王沁宽
 * @date 2019/3/4
 */
public class EmailServiceTest extends CommonTest {
    @Autowired
    EmailService emailService;

    @Test
    public void emailServiceTest() {
        UserT u = new UserT();
        u.setUserId(2);
        u.setEmail("wangqinkuan@qq.com");
        u.setUserName("无敌");

        emailService.sendValidEmail(u);
       try {
           Thread.sleep(10000);
       }catch (Exception e){
           e.toString();
       }
    }
}
