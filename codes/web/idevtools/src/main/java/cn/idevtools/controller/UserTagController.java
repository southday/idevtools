package cn.idevtools.controller;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.po.UserTagT;
import cn.idevtools.service.UserService;
import cn.idevtools.service.UserTagService;
import cn.idevtools.util.ValidUtil;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户标签的控制器
 * 该类管理用户标签
 * 主要用于用户标签的增删改查
 *
 * @author 王沁宽
 * @date 2019/2/27
 */

@Controller
@RequestMapping("/a/userTags")
public class UserTagController {

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private UserService userService;

    /**
     * 根据用户id与标签id为相应的用户添加标签
     * 王沁宽 2019.02.27
     * @param userId 用户id
     * @param tagId 标签id
     * @return
     */
    @ResponseJSONP
    @PostMapping("/{userId}/{tagId}")
    public Message addTagForUser(@PathVariable Integer userId,@PathVariable Integer tagId) {
        return new Message(
                userService.addTagForUser(userId,tagId) == 0 ?
                        CodeMsgE.INSERT_FAILURE :
                        CodeMsgE.INSERT_SUCCESS
        );
    }

    /**
     * 根据用户id与标签id为相应的用户删除对应标签
     * 王沁宽 2019.02.27
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0:失败 1:成功
     */
    @ResponseJSONP
    @DeleteMapping("/{userId}/{tagId}")
    public Message removeTagForUser(@PathVariable Integer userId,@PathVariable Integer tagId) {
        return new Message(
                userService.removeTagForUser(userId,tagId) == 0 ?
                        CodeMsgE.DELETE_FAILURE :
                        CodeMsgE.DELETE_SUCCESS
        );
    }

    /**
     * 新建用户标签 tagCode不得重复
     * 王沁宽 2019.02.27
     * @param userTag
     * @return 表单验证失败则返回错误信息,否则返回操作状态
     */
    @ResponseJSONP
    @PostMapping("")
    public Message addUserTag(@Valid UserTagT userTag, BindingResult bindingResult) {
        Message message;
        if (bindingResult.hasErrors()) {
            message = new Message<>(CodeMsgE.VALID_ERROR,ValidUtil.toValidMsgs(bindingResult));
        } else {
            message = new Message<>(
                    userTagService.addUserTag(userTag) == 0 ?
                            CodeMsgE.INSERT_FAILURE :
                            CodeMsgE.INSERT_SUCCESS
                    );
        }
        return message;
    }

    /**
     * 删除用户标签
     * 王沁宽 2019.02.27
     * @param tagId
     * @return 操作状态
     */
    @ResponseJSONP
    @DeleteMapping("/{tagId}")
    public Message addUserTag(@PathVariable Integer tagId) {
        return new Message(
            userTagService.deleteUserTagById(tagId) == 0 ?
                    CodeMsgE.INSERT_FAILURE :
                    CodeMsgE.INSERT_SUCCESS
        );
    }

    /**
     * 更新用户标签
     * 王沁宽 2019.02.27
     * @param userTag 待更新的用户标签，id不能修改，tagCode不能重复
     * @return 操作状态
     */
    @ResponseJSONP
    @PutMapping("")
    public Message updateUserTag(UserTagT userTag) {
        return new Message(
                userTagService.updateUserTag(userTag) == 0 ?
                        CodeMsgE.UPDATE_FAILURE :
                        CodeMsgE.UPDATE_SUCCESS
        );
    }
}
