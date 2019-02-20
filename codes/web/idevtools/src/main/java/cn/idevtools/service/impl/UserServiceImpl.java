package cn.idevtools.service.impl;


import cn.idevtools.mapper.UserTMapper;
import cn.idevtools.po.UserT;
import cn.idevtools.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用于实现管理员用户管理模块的接口.
 *
 * @author 王沁宽
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserTMapper userTMapper;

    /**
     * 调用UserTMapper中的selectUserPage得到分页查询用户信息     *
     */
    @Override
    public List<UserT> getAllUserPage(Map<String, Object> pageParams) {
        return userTMapper.selectUserPage(pageParams);
    }

    /**
     * 使用分页插件获取全部用户的基本信息
     * @param pageNum 页号
     * @param pageSize 每页显示量
     *
     * @return 被PageInfo包装后的查询分页结果
     */
    @Override
    public PageInfo<UserT> getAllUserPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserT> user=userTMapper.selectAllUser();

        return new PageInfo<UserT>(user);
    }

    /**
     * 根据用户id，将其valid字段改为0，标记为无效
     */
    @Override
    public int deleteUser(UserT user) {
        return userTMapper.updateUserValidById(user);
    }

    /**
     * 更新用户的基本信息
     * @param user 待修改的用户对象
     * @return 影响行数，修改用户只操作单个对象，故成功返回1
     */
    @Override
    public int updateUser(UserT user) {
        return userTMapper.updateUser(user);
    }

    @Override
    public int createUser(UserT user) {
        return 0;
    }

    /**
     * 查询符合条件的用户并分页
     * @param user 待查询的用户对象
     *
     * @param pageNum
     * @param pageSize
     * @return 被PageInfo包装后的查询分页结果
     */
    @Override
    public PageInfo<UserT> getUsersPage(UserT user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserT> users=userTMapper.selectUsers(user);
        return new PageInfo<UserT>(users);
    }

    /**
     * 根据用户id得到附带标签信息的用户详细信息。
     * 包含的属性字段有:用户的全部属性字段,用户对应标签的全部属性字段
     * @param userId 用户id
     * @return 附带标签信息的单个用户详细信息
     */
    @Override
    public UserT getUserDetailWithTagById(Integer userId) {
        return userTMapper.selectUserDetailWithTagById(userId);
    }
}
