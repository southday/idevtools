package cn.idevtools.mapper;
import cn.idevtools.po.UserTagT;

public interface UserTagTMapper {
    /**
     * 插入用户标签，tagCode属性不得重复
     * @param userTag
     * @return 成功1 失败0
     */
    int insertUserTag(UserTagT userTag);

    /**
     * 根据id删除标签
     * @param tagId
     * @return 成功1 失败0
     */
    int deleteUserTagById(Integer tagId);
}