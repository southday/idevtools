package cn.idevtools.service.impl;

import cn.idevtools.po.UserT;
import cn.idevtools.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务实现
 * @author 王沁宽
 * @date 2019/3/4
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

    /**
     * 发邮件用的邮箱
     */
    public static final String mailHost = "wangqinkuan@qq.com";

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void sendEmail(final MimeMessage message) {
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("send");
                mailSender.send(message);
            }
        });
    }

    @Override
    public void sendValidEmail(UserT user) {
        //标题
        String subject = "用户" + user.getUserName() + "注册idevtools的验证邮件";
        //验证链接
        String link = "http://localhost:8080/idevtools/u/active/" + user.getUserId();
        //内容
        String text = "点击本条链接进行验证 " + link;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(mailHost);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(text);
            sendEmail(message);
        }
        catch (MessagingException e){
            e.toString();
        }
    }
}
