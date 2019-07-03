package cn.idevtools.controller;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.CommonConst;
import cn.idevtools.common.Message;
import cn.idevtools.common.StatusCode;
import cn.idevtools.po.*;
import cn.idevtools.redis.Recommend;
import cn.idevtools.redis.RedisUtil;
import cn.idevtools.service.CommonService;
import cn.idevtools.service.EmailService;
import cn.idevtools.service.UserService;
import cn.idevtools.service.impl.EmailServiceImpl;
import cn.idevtools.util.*;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 该类用于实现管理员用户管理模块.
 * 主要用于管理员用户的增删改查
 * @see UserService
 *
 * @author 王沁宽
 * @date 2019/2/27
 */
@Controller
@RequestMapping("/u")
public class UserController {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CommonService commonService;

    /**
     * 用户登陆 southday 2019.02.28
     * @param argUser
     * @param bindingResult
     * @return
     */
    @ResponseJSONP
    @PostMapping("/login")
    public Message<?> login(@Valid UserT argUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        argUser.setPassword(MD5Util.md5salt(argUser.getPassword()));
        UserT user = userService.login(argUser);
        Message<UserT> ret = new Message<>(CodeMsgE.LOGIN_SUCCESS, user);
        if (user == null) {
            ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_INPUT_ERROR);
        } else {
            boolean success = JWTer.addLoginedToken(user.getUserId(), user.getUserName(), CommonConst.USER_TYPE_USER);
            if (!success)
                ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_TOKEN_ERROR);
        }
        return ret;
    }

    /**
     * 用户注册 southday 2019.02.28
     * @param argUser
     * @param bindingResult
     * @return
     */
    @ResponseJSONP
    @PostMapping("/join")
    public Message<?> join(@Valid UserT argUser, BindingResult bindingResult, HttpServletRequest req) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        String password2 = req.getParameter("password2");
        if (!argUser.getPassword().equals(password2))
            return new Message<>(StatusCode.FAILURE, "注册失败，两次密码不一致");
        boolean userNameExists = userService.isUserNameExists(argUser.getUserName());
        boolean emailExists = userService.isEmailExists(argUser.getEmail());
        String msg = null;
        if (userNameExists && !emailExists)
            msg = "注册失败，用户名已被注册";
        else if (!userNameExists && emailExists)
            msg = "注册失败，邮箱已被注册";
        else if (userNameExists && emailExists)
            msg = "注册失败，用户名和邮箱均已被注册";
        if (msg != null) {
            return new Message<>(StatusCode.FAILURE, msg);
        } else {
            argUser.setPassword(MD5Util.md5salt(argUser.getPassword()));
            boolean joinSuccess = userService.join(argUser);
            if (!joinSuccess) {
                return new Message<>(StatusCode.FAILURE, "注册失败，请稍后重试");
            } else {
                argUser.setPassword(null);
                boolean addTokenSuccess = JWTer.addLoginedToken(argUser.getUserId(), argUser.getUserName(), CommonConst.USER_TYPE_USER);
                //发送验证邮件
                emailService.sendValidEmail(argUser);
                msg = addTokenSuccess ? "注册成功" : "注册成功，Token创建异常";
                return new Message<>(StatusCode.SUCCESS, msg, argUser);
            }
        }
    }

    /**
     * 用户退出登录
     * southday 2019.03.05
     * @return
     */
    @ResponseJSONP
    @PostMapping("/logout")
    public Message<?> logout() {
        return userService.logout();
    }

    /**
     * 根据token获取用户信息
     * southday 2019.03.05
     * @return
     */
    @ResponseJSONP
    @GetMapping("/userInfo")
    public Message<?> getUserInfo() {
        JWTer jwter = new JWTer(JWTer.getToken());
        if (!jwter.isUsable())
            return new Message<>(StatusCode.FAILURE, "获取用户信息失败");
        UserT user = userService.getUserByUserId(jwter.getId());
        return user != null ?
                new Message<>(StatusCode.SUCCESS, "获取用户信息成功", user) :
                new Message<>(StatusCode.FAILURE, "获取用户信息失败");
    }

    /**
     * 激活用户
     * 王沁宽 2019.03.09
     */
    @ResponseJSONP
    @GetMapping("/active")
    public Message<?> activeUser(@RequestParam("activeCode") String activeCode) throws Exception{
        //解析激活码
        String decryptedActiveCode = DESCipher.getInstance().decrypt(activeCode);
        int expireDateIndex = decryptedActiveCode.length() - EmailServiceImpl.MAIL_DATE_PARTTEN.length();
        String expireDate = decryptedActiveCode.substring(expireDateIndex);
        Integer userId = Integer.valueOf(decryptedActiveCode.substring(0,expireDateIndex));
        //如果过期直接激活失败
        if((new Date()).after(EmailServiceImpl.MAIL_DATE_FORMAT.parse(expireDate))) return new Message<>(StatusCode.FAILURE,"激活邮件过期");
        return userService.activeUser(userId) ?
                new Message<>(StatusCode.SUCCESS,"激活成功") :
                new Message<>(StatusCode.FAILURE,"激活失败");
    }

    /**
     * 用户下载工具 southday 2019.03.18
     * @param toolId
     * @return
     */
    @ResponseJSONP
    @PostMapping("/downloads/{toolId}")
    public Message<?> downloadTool(@PathVariable Integer toolId) {
        JWTer jwter = new JWTer(JWTer.getToken());
        if (!jwter.isUsable())
            return new Message<>(CodeMsgE.INSERT_FAILURE);
        //表示用户喜欢这个工具(统计后用于推荐)
        redisUtil.LikeTool(jwter.getId(),toolId);

        DownloadsT download = new DownloadsT();
        download.setUserId(jwter.getId());
        download.setToolId(toolId);
        boolean success = userService.downloadTool(download);
        return success ?
                new Message<>(CodeMsgE.INSERT_SUCCESS) :
                new Message<>(CodeMsgE.INSERT_FAILURE);
    }

    /**
     * 用户收藏工具 southday 2019.03.18
     * @param toolId
     * @return
     */
    @ResponseJSONP
    @PostMapping("/collections/{toolId}")
    public Message<?> collectTool(@PathVariable Integer toolId) {
        JWTer jwTer = new JWTer(JWTer.getToken());
        if (!jwTer.isUsable())
            return new Message<>(CodeMsgE.INSERT_FAILURE);

        //表示用户喜欢这个工具(统计后用于推荐)
        redisUtil.LikeTool(jwTer.getId(),toolId);

        CollectionsT collection = new CollectionsT();
        collection.setUserId(jwTer.getId());
        collection.setToolId(toolId);
        boolean success = userService.collectTool(collection);
        return success ?
                new Message<>(CodeMsgE.INSERT_SUCCESS) :
                new Message<>(CodeMsgE.INSERT_FAILURE);
    }

    /**
     * 用户取消收藏 southday 2019.03.18
     * @param collectId
     * @return
     */
    @ResponseJSONP
    @DeleteMapping("/collections/{collectId}")
    public Message<?> cancelCollection(@PathVariable Integer collectId) {
        boolean success = userService.cancelCollection(collectId);
        return success ?
                new Message<>(CodeMsgE.DELETE_SUCCESS) :
                new Message<>(CodeMsgE.DELETE_FAILURE);
    }

    /**
     * 用户意见反馈 southday 2019.03.18
     * @param suggestion
     * @return
     */
    @ResponseJSONP
    @PostMapping("/suggestions")
    public Message<?> submitSuggestion(@Valid SuggestionsT suggestion, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        JWTer jwTer = new JWTer(JWTer.getToken());
        if (!jwTer.isUsable())
            return new Message<>(CodeMsgE.INSERT_FAILURE);
        suggestion.setUserId(jwTer.getId());
        // 1.意见反馈记录入库
        boolean success = userService.submitSuggestion(suggestion);
        // 2.发送邮件（待开发）
        return success ?
                new Message<>(StatusCode.SUCCESS, "感谢您的反馈！") :
                new Message<>(CodeMsgE.SUBMIT_FAILURE);
    }

    /**
     * 用户推荐工具 southday 2019.03.18
     * @param recommendation
     * @return
     */
    @ResponseJSONP
    @PostMapping("/recommendations")
    public Message<?> recommendTool(@Valid RecommendationsT recommendation, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        JWTer jwTer = new JWTer(JWTer.getToken());
        if (!jwTer.isUsable())
            return new Message<>(CodeMsgE.INSERT_FAILURE);
        recommendation.setUserId(jwTer.getId());
        // 1.工具推荐记录入库
        boolean success = userService.recommendTool(recommendation);
        // 2.发送邮件（待开发）
        return success ?
                new Message<>(StatusCode.SUCCESS, "感谢您的推荐！") :
                new Message<>(CodeMsgE.SUBMIT_FAILURE);
    }

    /**
     * 给用户推送n个与toolId类似的tool 王沁宽 2019.05.21
     * @return
     */
    @ResponseJSONP
    @GetMapping("/recommend/{toolId}")
    public Message<?> recommendToUser(@PathVariable Integer toolId){
        //要推送几个
        int n = 5;
        JWTer jwter = new JWTer(JWTer.getToken());
        if (!jwter.isUsable())
            return new Message<>(CodeMsgE.INSERT_FAILURE);
        try {
            List<RecommendedItem> recommendedItemList = Recommend.recommender.recommendedBecause(jwter.getId(),toolId,n);
            List<ToolT> tools = new ArrayList<>();
            for(RecommendedItem item: recommendedItemList){
                tools.add(commonService.getToolByToolId((int)item.getItemID()));
            }
            return new Message<>(CodeMsgE.QUERY_SUCCESS,tools);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Message<>(CodeMsgE.QUERY_FAILURE);
    }
}
