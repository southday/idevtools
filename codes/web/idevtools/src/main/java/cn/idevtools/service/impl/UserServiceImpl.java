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

    @Override
    public int updateUser(UserT user) {
        return userTMapper.updateUser(user);
    }

    @Override
    public int createUser(UserT user) {
        return 0;
    }

    @Override
    public PageInfo<UserT> getUsersPage(UserT user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserT> users=userTMapper.selectUsers(user);
        return new PageInfo<UserT>(users);
    }

    @Override
    public UserT getUserDetailWithTagById(Integer userId) {
        return userTMapper.selectUserDetailWithTagById(userId);
    }
}
