package cn.idevtools.mapper;
import cn.idevtools.po.UserTagT;

public interface UserTagTMapper {
    /**
     * 插入用户标签，tagCode属性不得重复 王沁宽 2019.02.26
     * @param userTag
     * @return 成功1 失败0
     */
    int insertUserTag(UserTagT userTag);

    /**
     * 根据id删除标签 王沁宽 2019.02.26
     * @param tagId
     * @return 成功1 失败0
     */
    int deleteUserTagById(Integer tagId);

    /**
     * 根据id更新标签，其中id不能更新，tagCode不得重复 王沁宽 2019.02.26
     * @param userTagT 待修改的用户
     * @return 成功1 失败0
     */
    int updateUserTagById(UserTagT userTagT);
}