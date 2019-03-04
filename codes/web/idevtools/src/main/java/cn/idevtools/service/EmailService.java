package cn.idevtools.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


/**
 * 邮件服务
 * @author 王沁宽
 * @date 2019/3/4
 */
@Service
public interface EmailService {
    /**
     * 发送简单邮件
     * @param mailMessage 指定from to text(内容) subject(标题)即可
     */
    void sendEmail(SimpleMailMessage mailMessage);
}
