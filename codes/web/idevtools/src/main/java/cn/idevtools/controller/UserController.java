package cn.idevtools.controller;


import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.CommonConst;
import cn.idevtools.common.Message;
import cn.idevtools.common.annotation.AddManageHistory;
import cn.idevtools.common.annotation.PrintExecTime;
import cn.idevtools.po.UserT;
import cn.idevtools.po.UserTagVO;
import cn.idevtools.service.UserService;
import cn.idevtools.util.CookieUtil;
import cn.idevtools.util.EncryptUtil;
import cn.idevtools.util.ValidUtil;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 该类用于实现管理员用户管理模块.
 * 主要用于管理员用户的增删改查
 * @see UserService
 *
 * @author 王沁宽
 */
@Controller
@RequestMapping("/u")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 该变量表示对用户数据分页时每页显示的数据量
     */
    private static final int pageSize=10;


    /**
     * 根据页号获取分页后的普通用户的基本json数据.
     *
     * @Param pageId 页号.
     */
    @RequestMapping(value = "/userinfo.json/page/{pageId}")
    @ResponseJSONP
    public Message<List<UserT>> getUserInfoJsonByPage(@PathVariable Integer pageId){

        return new Message<>(
                CodeMsgE.QUERY_SUCCESS,userService.getAllUserPage(pageId,pageSize).getList()
        );
    }


    /**
     * 根据用户id删除用户（不做真实删除，只标记valid为0）.
     *
     * @Param userId 用户id.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete/{userId}")
    public Message deleteUserById(@PathVariable Integer userId){
        return new Message(
                userService.deleteUser(userId) == 0 ?
                        CodeMsgE.DELETE_FAILURE :
                        CodeMsgE.DELETE_SUCCESS
        );
    }

    /**
     * 条件查询用户，获取分页结果
     * @param user 待查询的用户
     * @param pageId 页号
     */
    @ResponseJSONP
    @PostMapping(value = "/searchUserInfo.json/page/{pageId}")
    public Message<List<UserT>> getSearchedUserInfoByPage(UserT user,@PathVariable Integer pageId){
        return new Message<>(
                CodeMsgE.QUERY_SUCCESS,
                userService.getUsersPage(user,pageId,pageSize).getList()
        );
    }

    /**
     * 根据用户id获得带有用户标签信息的用户详细信息
     * 包含数据有:用户全部字段信息，用户对应标签全部字段信息
     */
    @ResponseJSONP
    @RequestMapping("/userDetailWithTag.json/{userId}")
    @PrintExecTime
    @AddManageHistory(ACTION_DESC = "王无敌到此一游")
    public Message<UserTagVO> getUserDetailWithTagById(@PathVariable Integer userId){
        return new Message<>(
                CodeMsgE.QUERY_SUCCESS,userService.getUserDetailWithTagById(userId)
        );
    }

    /**
     * 根据用户id与标签id为相应的用户添加标签
     * @param userId 用户id
     * @param tagId 标签id
     * @return
     */
    @ResponseJSONP
    @RequestMapping("/addTagForUser/{userId}/{tagId}")
    public Message addTagForUser(@PathVariable Integer userId,@PathVariable Integer tagId){
        return new Message(
                userService.addTagForUser(userId,tagId) == 0 ?
                        CodeMsgE.INSERT_FAILURE :
                        CodeMsgE.INSERT_SUCCESS
        );
    }

    /**
     * 根据用户id与标签id为相应的用户删除对应标签
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0:失败 1:成功
     */
    @ResponseJSONP
    @RequestMapping("/removeTagForUser/{userId}/{tagId}")
    public Message removeTagForUser(@PathVariable Integer userId,@PathVariable Integer tagId){
        return new Message(
                userService.removeTagForUser(userId,tagId) == 0 ?
                        CodeMsgE.DELETE_FAILURE :
                        CodeMsgE.DELETE_SUCCESS
        );
    }

    /**
     * 用户登陆 southday 2019.02.28
     * @param argUser
     * @param bindingResult
     * @return
     */
    @ResponseJSONP
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Message<?> login(@Valid UserT argUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        if (!ValidUtil.isPassCaptcha())
            return new Message<>(CodeMsgE.CAPTCHA_ERROR);
        argUser.setPassword(EncryptUtil.md5salt(argUser.getPassword()));
        UserT user = userService.login(argUser);
        Message<UserT> ret = new Message<>(CodeMsgE.LOGIN_SUCCESS, user);
        if (user == null) {
            ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_INPUT_ERROR);
        } else {
            boolean success = CookieUtil.addLoginedToken(user.getUserId(), user.getUserName(), CommonConst.USER_TYPE_USER);
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
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Message<?> join(@Valid UserT argUser, BindingResult bindingResult, HttpServletRequest req) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        if (!ValidUtil.isPassCaptcha(req))
            return new Message<>(CodeMsgE.CAPTCHA_ERROR);
        String password2 = req.getParameter("password2");
        if (!argUser.getPassword().equals(password2))
            return new Message<>(-1, "注册失败，两次密码不一致");
        boolean userNameExists = userService.isUserNameExists(argUser.getUserName());
        boolean emailExists = userService.isEmailExists(argUser.getEmail());
        String msg = null;
        if (userNameExists && !emailExists)
            msg = "注册失败，用户名已被注册";
        else if (!userNameExists && emailExists)
            msg = "注册失败，邮箱已被注册";
        else if (userNameExists && emailExists)
            msg = "注册失败，用户名和邮箱均已被注册";
        if (msg != null)
            return new Message<>(-1, msg);
        else {
            userService.join(argUser);
            boolean success = CookieUtil.addLoginedToken(argUser.getUserId(), argUser.getUserName(), CommonConst.USER_TYPE_USER);
            return success ?
                    new Message<>(1, "注册成功", argUser) :
                    new Message<>(-1, "注册失败", "Token创建异常");
        }
    }
}
