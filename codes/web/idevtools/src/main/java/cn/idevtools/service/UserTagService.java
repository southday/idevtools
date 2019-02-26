package cn.idevtools.service;
import cn.idevtools.po.UserTagT;
import cn.idevtools.service.impl.UserTagServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 管理员用户标签管理模块的接口.
 * 具体实现见
 * @see UserTagServiceImpl
 *
 * @author 王沁宽
 */
@Service
public interface UserTagService {
    /**
     * 新建用户标签
     * @param userTag
     * @return 成功1 失败0
     */
    public int addUserTag(UserTagT userTag);

    /**
     * 更新用户标签信息
     * @param userTag
     * @return 成功1 失败0
     */
    public int updateUserTag(UserTagT userTag);

    /**
     * 根据标签id删除相应标签
     * @param tagId
     * @return 成功1 失败0
     */
    public int deleteUserTagById(Integer tagId);

}
