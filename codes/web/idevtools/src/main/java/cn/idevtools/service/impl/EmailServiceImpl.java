package cn.idevtools.service.impl;

import cn.idevtools.po.UserT;
import cn.idevtools.service.EmailService;
import cn.idevtools.util.DESCipher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    public static final String MAIL_HOST = "wangqinkuan@qq.com";

    /**
     * 激活邮件有效时间 单位 分钟
     */
    public static final int ACTIVE_DURATION = 30;
    /**
     * 邮件日期格式
     */
    public static final String MAIL_DATE_PARTTEN = "yyyyMMddHHmmss";

    public static final SimpleDateFormat MAIL_DATE_FORMAT = new SimpleDateFormat(MAIL_DATE_PARTTEN);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void sendEmail(final MimeMessage message) {
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mailSender.send(message);
            }
        });
    }

    @Override
    public void sendValidEmail(UserT user) {
        //截止时间
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE,ACTIVE_DURATION);
        Date expireDate = now.getTime();
        try {
            String userId = String.valueOf(user.getUserId());
            String expireDateString = MAIL_DATE_FORMAT.format(expireDate);
            //拼接id与日期并加密 生成激活码
            String activeCode = DESCipher.getInstance().encrypt(userId + expireDateString);
            //标题
            String subject = "用户" + user.getUserName() + "注册idevtools的验证邮件";
            // 验证链接，激活码可能会包含/ 因此不用路径获取值，部署到本地服务器上的激活链接
//            String link = String.format("http://localhost:8080/idevtools/u/active?activeCode=%s",activeCode);
            // 部署到服务器上的激活链接
            String link = String.format("https://idevtools.cn/u/active?activeCode=%s",activeCode);
            //内容
            String text = "点击本条链接进行验证" + link;
            //发邮件
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(MAIL_HOST);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(text);
            sendEmail(message);
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
