package cn.idevtools.controller;

import cn.idevtools.po.UserTagT;
import cn.idevtools.service.UserTagService;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户标签的控制器
 * 该类管理用户标签
 * 主要用于用户标签的增删改查
 *
 * @author 王沁宽
 */

@Controller
@RequestMapping("/userTag")
public class UserTagController {

    @Autowired
    UserTagService userTagService;

    /**
     * 新建用户标签 tagCode不得重复
     * @param userTag
     * @return 成功1 失败0
     */
    @RequestMapping("/add")
    @ResponseJSONP
    public int addUserTag(UserTagT userTag){
        return userTagService.addUserTag(userTag);
    }

    /**
     * 删除用户标签
     * @param tagId
     * @return 成功1 失败0
     */
    @RequestMapping("/delete/{tagId}")
    @ResponseJSONP
    public int addUserTag(@PathVariable Integer tagId){
        return userTagService.deleteUserTagById(tagId);
    }
}
