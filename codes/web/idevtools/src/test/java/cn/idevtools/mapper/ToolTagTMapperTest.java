package cn.idevtools.mapper;

import cn.idevtools.CommonTest;
import cn.idevtools.po.ToolTagT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @see ToolTagTMapper
 * 测试该接口的sql
 *
 * @author 王沁宽
 * @date 2019/3/7
 */
public class ToolTagTMapperTest extends CommonTest {
    @Autowired
    ToolTagTMapper toolTagTMapper;

    @Test
    public void toolTagTMapperTest(){

        toolTagTMapper.selectAllToolTag();

        ToolTagT toolTagT = new ToolTagT();
        toolTagTMapper.selectToolTag(toolTagT);

        toolTagTMapper.deleteToolTagById(1);

        toolTagT.setDescription("update");
        toolTagTMapper.updateToolTag(toolTagT);

        ToolTagT toolTagT2 = new ToolTagT();
        toolTagT2.setDescription("insert");
        toolTagTMapper.insertToolTag(toolTagT2);
    }
}
