package cn.idevtools.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.validation.Valid;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 发送邮件相关配置文件
 * @author 王沁宽
 * @date 2019/3/4
 */

@Configuration
@PropertySource("classpath:/config/mailserver.properties")
public class MailConfig {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(env.getProperty("mailserver.username"));
        mailSender.setPassword(env.getProperty("mailserver.password"));
        mailSender.setPort(Integer.valueOf(env.getProperty("mailserver.port")));
        mailSender.setHost(env.getProperty("mailserver.host"));
        return mailSender;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        threadPoolTaskExecutor.setCorePoolSize(5);
        //允许的空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(200);
        //线程池维护线程的最大数量
        threadPoolTaskExecutor.setMaxPoolSize(10);
        //缓存队列
        threadPoolTaskExecutor.setQueueCapacity(20);
        //对拒绝task的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return threadPoolTaskExecutor;
    }
}
