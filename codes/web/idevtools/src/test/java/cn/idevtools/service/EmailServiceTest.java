package cn.idevtools.service;

import cn.idevtools.CommonTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

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

        for(int i=0;i<10;i++){
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom("wangqinkuan@qq.com");
            mailMessage.setTo("wangqinkuan@qq.com");
            mailMessage.setText(String.valueOf(i));
            emailService.sendEmail(mailMessage);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
