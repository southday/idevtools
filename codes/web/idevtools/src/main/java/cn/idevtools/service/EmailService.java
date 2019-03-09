package cn.idevtools.service;

import cn.idevtools.po.UserT;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;


/**
 * 邮件服务
 * @author 王沁宽
 * @date 2019/3/4
 */
@Service
public interface EmailService {
    /**
     * 发送邮件
     * @param message
     */
    void sendEmail(MimeMessage message);

    /**
     * 发送验证邮件
     * @param user 待验证的用户
     */
    void sendValidEmail (UserT user);
}
