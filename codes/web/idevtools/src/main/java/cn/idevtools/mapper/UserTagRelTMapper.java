package cn.idevtools.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserTagRelTMapper {
    /**
     * 根据用户id与标签id，插入user_tag_rel_t表，其中userId与tagId不能同时重复，
     * 即不能为同一个用户插入重复的标签 王沁宽 2019.02.26
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0表示失败，1表示成功(重复也输入失败情况，返回0)
     */
    int insertUserTagRel(@Param("userId") Integer userId,@Param("tagId") Integer tagId);

    /**
     * 根据用户id与标签id为用户删除对应的标签 王沁宽 2019.02.26
     * @param userId 用户id
     * @param tagId 标签id
     * @return 0表示失败，1表示成功
     */
    int deleteUserTagRelByTagIdAndUserId(@Param("userId") Integer userId,@Param("tagId") Integer tagId);
}