package cn.idevtools.service.impl;


import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.mapper.CollectionsTMapper;
import cn.idevtools.mapper.DownloadsTMapper;
import cn.idevtools.mapper.UserTMapper;
import cn.idevtools.mapper.UserTagRelTMapper;
import cn.idevtools.po.CollectionsT;
import cn.idevtools.po.DownloadsT;
import cn.idevtools.po.UserT;
import cn.idevtools.po.UserTagVO;
import cn.idevtools.service.UserService;
import cn.idevtools.util.JWTer;
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
 * @date 2019/2/27
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserTMapper userTMapper;

    @Autowired
    private UserTagRelTMapper userTagRelTMapper;

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

        return new PageInfo<>(user);
    }

    /**
     * 根据用户id，将其valid字段改为0，标记为无效
     */
    @Override
    public int deleteUser(Integer userId) {
        return userTMapper.updateUserValidById(userId);
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
        return new PageInfo<>(users);
    }

    /**
     * 根据用户id得到附带标签信息的用户详细信息。
     * 包含的属性字段有:用户的全部属性字段,用户对应标签的全部属性字段
     * @param userId 用户id
     * @return 附带标签信息的单个用户详细信息
     */
    @Override
    public UserTagVO getUserDetailWithTagById(Integer userId) {
        return userTMapper.selectUserDetailWithTagById(userId);
    }

    /**
     * 根据用户id与标签id为相应的用户添加标签
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0:添加失败 1:添加成功
     */

    @Override
    public int addTagForUser(Integer userId, Integer tagId) {
        return userTagRelTMapper.insertUserTagRel(userId,tagId);
    }

    /**
     * 根据用户id与标签id为对应用户删除标签
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0:失败 1:成功
     */
    @Override
    public int removeTagForUser(Integer userId, Integer tagId) {
        return userTagRelTMapper.deleteUserTagRelByTagIdAndUserId(userId,tagId);
    }

    @Override
    public boolean isUserExists(String userName) {
        return userTMapper.selectUserCountByName(userName) != 0;
    }

    @Override
    public boolean isUserNameExists(String userName) {
        return userTMapper.selectUserCountByName(userName) != 0;
    }

    @Override
    public boolean isEmailExists(String email) {
        return userTMapper.selectUserCountByEmail(email) != 0;
    }

    @Override
    public UserT login(UserT argUser) {
        return userTMapper.selectUserByNamePassword(argUser);
    }

    @Override
    public boolean activeUser(Integer userId) {
        return userTMapper.updateActiveByUserId(userId) == 1;
    }

    @Autowired
    private DownloadsTMapper downloadsTMapper;

    @Override
    public boolean downloadTool(DownloadsT download) {
        return downloadsTMapper.insertDownload(download) == 1;
    }

    @Autowired
    private CollectionsTMapper collectionsTMapper;

    @Override
    public boolean collectTool(CollectionsT collection) {
        return collectionsTMapper.insertCollection(collection) == 1;
    }

    @Override
    public boolean cancelCollection(Integer collectId) {
        return collectionsTMapper.deleteCollectionByCollectId(collectId) == 1;
    }

    @Override
    public boolean join(UserT argUser) {
        return userTMapper.insertUser(argUser) == 1;
    }

    @Override
    public Message<?> logout() {
        return JWTer.disableLoginedToken() ?
                new Message<>(CodeMsgE.LOGOUT_SUCCESS) :
                new Message<>(CodeMsgE.LOGOUT_ERROR);
    }

    @Override
    public UserT getUserByUserId(Integer userId) {
        return userTMapper.selectUserByUserId(userId);
    }
}
