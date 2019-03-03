package cn.idevtools.controller;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.po.UserTagT;
import cn.idevtools.service.UserTagService;
import cn.idevtools.util.ValidUtil;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/userTag")
public class UserTagController {

    @Autowired
    UserTagService userTagService;

    /**
     * 新建用户标签 tagCode不得重复
     * @param userTag
     * @return 表单验证失败则返回错误信息,否则返回操作状态
     */
    @RequestMapping("/add")
    @ResponseJSONP
    public Message addUserTag(@Valid UserTagT userTag, BindingResult bindingResult){
        Message message;
        if(bindingResult.hasErrors()){
            message = new Message<>(CodeMsgE.VALID_ERROR,ValidUtil.toValidMsgs(bindingResult));
        }
        else{
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
     * @param tagId
     * @return 操作状态
     */
    @RequestMapping("/delete/{tagId}")
    @ResponseJSONP
    public Message addUserTag(@PathVariable Integer tagId){
        return new Message(
            userTagService.deleteUserTagById(tagId) == 0 ?
                    CodeMsgE.INSERT_FAILURE :
                    CodeMsgE.INSERT_SUCCESS
        );
    }

    /**
     * 更新用户标签
     * @param userTag 待更新的用户标签，id不能修改，tagCode不能重复
     * @return 操作状态
     */
    @PostMapping("/update")
    @ResponseJSONP
    public Message updateUserTag(UserTagT userTag){
        return new Message(
                userTagService.updateUserTag(userTag) == 0 ?
                        CodeMsgE.UPDATE_FAILURE :
                        CodeMsgE.UPDATE_SUCCESS
        );
    }
}
