package cn.idevtools.mapper;

import cn.idevtools.po.CollectionToolVO;
import cn.idevtools.po.CollectionsT;

import java.util.List;

public interface CollectionsTMapper {
    /**
     * 根据用户id查询用户的收藏工具 王沁宽 2019.03.03
     * @param userId
     * @return
     */
    List<CollectionToolVO> selectCollectionToolByUserId(Integer userId);
    /**
     * 根据collectId删除一条收藏记录 王沁宽 2019.03.03
     * @param collectId
     * @return
     */
    int deleteCollectionByCollectId(Integer collectId);

    /**
     * 插入一条收藏记录，时间为now() 王沁宽 2019.03.03
     * @param collection
     * @return
     */
    int insertCollection(CollectionsT collection);

}