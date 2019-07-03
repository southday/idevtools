package cn.idevtools.controller;


import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.common.StatusCode;
import cn.idevtools.common.annotation.AddManageHistory;
import cn.idevtools.po.MessageT;
import cn.idevtools.po.SuggestionsT;
import cn.idevtools.po.UserT;
import cn.idevtools.service.EmailService;
import cn.idevtools.service.MessageService;
import cn.idevtools.service.UserService;
import cn.idevtools.util.JWTer;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.internet.MimeMessage;

import java.util.List;

import static cn.idevtools.service.impl.EmailServiceImpl.MAIL_HOST;

@Controller
@RequestMapping("/m")
public class MessageController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @ResponseJSONP
    @PostMapping("/reply")
    @AddManageHistory(ACTION_DESC = "回复用户消息")
    public Message<?> recommendTool(MessageT messageT) {

        UserT user = userService.getUserByUserId(messageT.getSenderId());

        JWTer jwTer = new JWTer(JWTer.getToken());
        if (!jwTer.isUsable())
            return new Message<>(CodeMsgE.INSERT_FAILURE);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(MAIL_HOST);
            helper.setTo(user.getEmail());
            helper.setSubject("这是一份回复邮件");
            helper.setText(messageT.getContent());
            emailService.sendEmail(message);
            messageService.replyMessge(messageT.getMsgId());
        }catch (Exception e){

        }

        boolean success = true;
        // 2.发送邮件（待开发）
        return success ?
                new Message<>(StatusCode.SUCCESS, "") :
                new Message<>(CodeMsgE.SUBMIT_FAILURE);
    }


    @GetMapping("/sug")
    @ResponseJSONP
    public Message getSuggestion() {
        List<MessageT> result = messageService.getSuggestion();
        return result == null ? new Message(CodeMsgE.QUERY_FAILURE,"获取信息失败") :
                new Message(CodeMsgE.QUERY_SUCCESS,result);
    }

    @GetMapping("/re")
    @ResponseJSONP
    public Message getRecommendation() {
        List<MessageT> result = messageService.getRecommendation();
        return result == null ? new Message(CodeMsgE.QUERY_FAILURE,"获取信息失败") :
                new Message(CodeMsgE.QUERY_SUCCESS,result);
    }

    @GetMapping("/sugu")
    @ResponseJSONP
    public Message getSuggestionById() {
        JWTer jwTer = new JWTer(JWTer.getToken());
        if (!jwTer.isUsable())
            return new Message<>(CodeMsgE.INSERT_FAILURE);
        List<MessageT> result = messageService.getSuggestionByUid(jwTer.getId());
        return result == null ? new Message(CodeMsgE.QUERY_FAILURE,"获取信息失败") :
                new Message(CodeMsgE.QUERY_SUCCESS,result);
    }

    @GetMapping("/reu")
    @ResponseJSONP
    public Message getRecommendationByUid() {
        JWTer jwTer = new JWTer(JWTer.getToken());
        System.out.println(jwTer.getUserName());
        if (!jwTer.isUsable())
            return new Message<>(CodeMsgE.INSERT_FAILURE);
        List<MessageT> result = messageService.getRecommendationByUid(jwTer.getId());
        return result == null ? new Message(CodeMsgE.QUERY_FAILURE,"获取信息失败") :
                new Message(CodeMsgE.QUERY_SUCCESS,result);
    }


}
