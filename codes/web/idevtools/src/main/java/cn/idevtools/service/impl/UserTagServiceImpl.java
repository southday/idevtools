package cn.idevtools.service.impl;

import cn.idevtools.mapper.UserTagTMapper;
import cn.idevtools.po.UserTagT;
import cn.idevtools.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用于实现管理员用户标签管理模块的接口.
 *
 * @author 王沁宽
 */
@Service
public class UserTagServiceImpl implements UserTagService {

    @Autowired
    UserTagTMapper userTagTMapper;

    /**
     * 新建用户标签,其中TagCode属性不得重复
     * @param userTag
     * @return 成功1 失败0
     */
    @Override
    public int addUserTag(UserTagT userTag) {
        return userTagTMapper.insertUserTag(userTag);
    }

    /**
     * 更新用户标签信息
     * @param userTag
     * @return 成功1 失败0
     */
    @Override
    public int updateUserTag(UserTagT userTag) {
        return userTagTMapper.updateUserTagById(userTag);
    }

    /**
     * 根据标签id删除相应标签
     * @param tagId
     * @return 成功1 失败0
     */
    @Override
    public int deleteUserTagById(Integer tagId) {
        return userTagTMapper.deleteUserTagById(tagId);
    }
}
