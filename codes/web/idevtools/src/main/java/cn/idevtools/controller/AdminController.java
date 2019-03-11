package cn.idevtools.controller;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.CommonConst;
import cn.idevtools.common.Message;
import cn.idevtools.common.annotation.AddManageHistory;
import cn.idevtools.common.annotation.PrintExecTime;
import cn.idevtools.po.AdminT;
import cn.idevtools.po.UserT;
import cn.idevtools.po.UserTagVO;
import cn.idevtools.service.AdminService;
import cn.idevtools.service.UserService;
import cn.idevtools.util.MD5Util;
import cn.idevtools.util.JWTer;
import cn.idevtools.util.ValidUtil;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 管理员相关业务模块 Controller
 * @author southday
 * @date 2019/2/26
 * @see AdminService
 */
@Controller
@RequestMapping("/a")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录
     * southday 2019.03.01
     * @param argAdmin
     * @param bindingResult
     * @return
     */
    @ResponseJSONP
    @PostMapping("/login")
    public Message<?> login(@Valid AdminT argAdmin, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new Message<>(CodeMsgE.VALID_ERROR, ValidUtil.toValidMsgs(bindingResult));
        if (!ValidUtil.isPassCaptcha())
            return new Message<>(CodeMsgE.CAPTCHA_ERROR);
        argAdmin.setPassword(MD5Util.md5salt(argAdmin.getPassword()));
        AdminT admin = adminService.login(argAdmin);
        Message<AdminT> ret = new Message<>(CodeMsgE.LOGIN_SUCCESS, admin);
        if (admin == null) {
            ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_INPUT_ERROR);
        } else {
            boolean success = JWTer.addLoginedToken(admin.getAdminId(), admin.getAdminName(), CommonConst.USER_TYPE_ADMIN);
            if (!success)
                ret.setCodeMsg(CodeMsgE.LOGIN_FAILURE_TOKEN_ERROR);
        }
        return ret;
    }

    /**
     * 管理员退出登录
     * southday 2019.03.01
     * @return
     */
    @ResponseJSONP
    @PostMapping("/logout")
    public Message<?> logout() {
        return adminService.logout();
    }

    @Autowired
    private UserService userService;

    /**
     * 该变量表示对用户数据分页时每页显示的数据量
     */
    private static final int pageSize = 10;

    /**
     * 条件查询用户，获取分页结果（待修改，与getUserInfoJsonByPage合并为一个方法）
     * 王沁宽 2019.02.27
     * @param user 待查询的用户
     * @param pageId 页号
     */
    @ResponseJSONP
    @PostMapping("/users/page/{pageId}")
    public Message<List<UserT>> getSearchedUserInfoByPage(UserT user,@PathVariable Integer pageId){
        return new Message<>(
                CodeMsgE.QUERY_SUCCESS,
                userService.getUsersPage(user,pageId,pageSize).getList()
        );
    }

    /**
     * 根据用户id删除用户（不做真实删除，只标记valid为0）.
     * 王沁宽 2019.02.27
     * @Param userId 用户id.
     * @return
     */
    @ResponseJSONP
    @DeleteMapping("/users/{userId}")
    public Message deleteUserById(@PathVariable Integer userId){
        return new Message(
                userService.deleteUser(userId) == 0 ?
                        CodeMsgE.DELETE_FAILURE :
                        CodeMsgE.DELETE_SUCCESS
        );
    }

    /**
     * 根据用户id获得带有用户标签信息的用户详细信息
     * 包含数据有:用户全部字段信息，用户对应标签全部字段信息
     * 王沁宽 2019.02.27
     */
    @ResponseJSONP
    @PrintExecTime
    @AddManageHistory(ACTION_DESC = "王无敌到此一游")
    @GetMapping("/usersWithTags/{userId}")
    public Message<UserTagVO> getUserDetailWithTagById(@PathVariable Integer userId){
        return new Message<>(
                CodeMsgE.QUERY_SUCCESS,userService.getUserDetailWithTagById(userId)
        );
    }
}
