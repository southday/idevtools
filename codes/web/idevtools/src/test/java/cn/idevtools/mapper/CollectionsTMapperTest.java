package cn.idevtools.mapper;

import cn.idevtools.CommonTest;
import cn.idevtools.po.CollectionsT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试 CollectionsTMapper sql
 *
 * @author 王沁宽
 * @date 2019/3/3
 */
public class CollectionsTMapperTest extends CommonTest {

    @Autowired CollectionsTMapper collectionsTMapper;

    @Test
    public void collectionMapperTest(){
        collectionsTMapper.selectCollectionToolByUserId(1);
        collectionsTMapper.deleteCollectionByCollectId(1);

        CollectionsT collectionsT=new CollectionsT();
        collectionsT.setResId(1);
        collectionsT.setUserId(1);
        collectionsTMapper.insertCollection(collectionsT);
    }
}
