package cn.idevtools.mapper;

import cn.idevtools.CommonTest;
import cn.idevtools.po.ToolT;
import cn.idevtools.po.ToolTagVO;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @see ToolTMapper
 * 测试该接口的sql
 *
 * @author 王沁宽
 * @date 2019/3/7
 */
public class ToolTMapperTest extends CommonTest {

    @Autowired
    ToolTMapper toolTMapper;

    @Test
    public void toolTMapperTest(){
        toolTMapper.selectAllTool();

        ToolT tool=new ToolT();
        tool.setToolId(1);

        toolTMapper.selectTool(tool);

        toolTMapper.selectToolWithTagById(1);

        tool.setToolName("无敌");

        toolTMapper.updateTool(tool);

        ToolT tool2=new ToolT();
        tool2.setToolName("insert");

        toolTMapper.insertTool(tool2);
        toolTMapper.deleteToolById(2);
    }

    @Test
    public void getToolWithTagsTest() {
        ToolTagVO vo = toolTMapper.selectToolWithTagById(1);
        System.out.println(JSON.toJSON(vo));
    }
}
