package cn.idevtools.controller;


import cn.idevtools.common.CodeMsg;
import cn.idevtools.common.Message;
import cn.idevtools.common.annotation.AddManageHistory;
import cn.idevtools.common.annotation.PrintExecTime;
import cn.idevtools.po.UserT;
import cn.idevtools.po.UserTagVO;
import cn.idevtools.service.UserService;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 该类用于实现管理员用户管理模块.
 * 主要用于管理员用户的增删改查
 * @see UserService
 *
 * @author 王沁宽
 */
@Controller
@RequestMapping("/user")
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
                CodeMsg.QUERY_SUCCESS,userService.getAllUserPage(pageId,pageSize).getList()
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
                        CodeMsg.DELETE_FAILURE :
                        CodeMsg.DELETE_SUCCESS
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
                CodeMsg.QUERY_SUCCESS,
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
                CodeMsg.QUERY_SUCCESS,userService.getUserDetailWithTagById(userId)
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
                        CodeMsg.INSERT_FAILURE :
                        CodeMsg.INSERT_SUCCESS
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
                        CodeMsg.DELETE_FAILURE :
                        CodeMsg.DELETE_SUCCESS
        );
    }
}
