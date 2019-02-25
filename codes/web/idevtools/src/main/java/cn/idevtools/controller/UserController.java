package cn.idevtools.controller;


import cn.idevtools.po.UserT;
import cn.idevtools.po.UserTagVO;
import cn.idevtools.service.UserService;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @RequestMapping(value = "/userinfo.json/page/{pageId}",method = RequestMethod.GET)
    @ResponseJSONP
    public List<UserT> getUserInfoJsonByPage(@PathVariable Integer pageId){

        PageInfo<UserT> userPage=userService.getAllUserPage(pageId,pageSize);

        return userPage.getList();
    }


    /**
     * 根据用户id删除用户（不做真实删除，只标记valid为0）.
     *
     * @Param userId 用户id.
     *
     * @return 删除成功则返回1
     */
    @ResponseBody
    @RequestMapping("/delete/{userId}")
    public int deleteUserById(@PathVariable Integer userId){
        UserT user=new UserT();
        user.setUserId(userId);
        return userService.deleteUser(user);
    }

    /**
     * 条件查询用户，获取分页结果
     * @param user 待查询的用户
     * @param pageId 页号
     */
    @ResponseJSONP
    @RequestMapping(value = "/searchUserInfo.json/page/{pageId}",method = RequestMethod.POST)
    public List<UserT> getSearchedUserInfoByPage(UserT user,@PathVariable Integer pageId){
        PageInfo<UserT> userPage=userService.getUsersPage(user,pageId,pageSize);
        return userPage.getList();
    }

    /**
     * 根据用户id获得带有用户标签信息的用户详细信息
     * 包含数据有:用户全部字段信息，用户对应标签全部字段信息
     */
    @ResponseJSONP
    @RequestMapping("/userDetailWithTag.json/{userId}")
    public UserTagVO getUserDetailWithTagById(@PathVariable Integer userId){
        return userService.getUserDetailWithTagById(userId);
    }

    /**
     * 根据用户id与标签id为相应的用户添加标签
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0:添加失败 1:添加成功
     */
    @ResponseJSONP
    @RequestMapping("/addTagForUser/{userId}/{tagId}")
    public int addTagForUser(@PathVariable Integer userId,@PathVariable Integer tagId){
        return userService.addTagForUser(userId,tagId);
    }

    /**
     * 根据用户id与标签id为相应的用户删除对应标签
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0:失败 1:成功
     */
    @ResponseJSONP
    @RequestMapping("/removeTagForUser/{userId}/{tagId}")
    public int removeTagForUser(@PathVariable Integer userId,@PathVariable Integer tagId){
        return userService.removeTagForUser(userId,tagId);
    }
}
