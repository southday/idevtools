package cn.idevtools.service.impl;

import cn.idevtools.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * 邮件服务实现
 * @author 王沁宽
 * @date 2019/3/4
 */
@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void sendEmail(final SimpleMailMessage mailMessage) {
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String from = mailMessage.getFrom();
                String[] to = mailMessage.getTo();
                try{
                    if(from == null || from.trim().length() == 0 || to == null){
                        logger.error("请检查发送/接受地址是否为空");
                    }
                    else mailSender.send(mailMessage);
                }catch (MailException e){
                    if(to != null){
                        for(String t : to){
                            logger.error("从" + from + "到" + t + "的邮件发送失败");
                        }
                    }
                    logger.error(e.toString());
                }

            }
        });

    }
}
