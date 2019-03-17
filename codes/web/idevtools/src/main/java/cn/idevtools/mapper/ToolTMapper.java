package cn.idevtools.mapper;

import cn.idevtools.po.ToolT;
import cn.idevtools.po.ToolTagVO;
import java.util.List;


public interface ToolTMapper {
    /**
     * 查询全部tool 王沁宽 2019.03.07
     * @return
     */
    List<ToolT> selectAllTool();
    /**
     * 插入tool 王沁宽 2019.03.07
     * @return
     */
    int insertTool(ToolT tool);
    /**
     * 查询tool 王沁宽 2019.03.07
     * @return
     */
    List<ToolT> selectTool(ToolT tool);
    /**
     * 根据id删除tool 王沁宽 2019.03.07
     * @return
     */
    int deleteToolById(Integer toolId);
    /**
     * 更新tool 王沁宽 2019.03.07
     * @return
     */
    int updateTool(ToolT tool);
    /**
     * 查询带有标签信息的tool 王沁宽 2019.03.07
     * @return
     */
    ToolTagVO selectToolWithTagById(Integer toolId);

    /**
     * 根据toolId查询指定工具
     * southday 2019.03.07
     * @param toolId
     * @return
     */
    ToolT selectToolByToolId(Integer toolId);

    /**
     * 根据toolName来查询工具信息
     * southday 2019.03.12
     * @param toolName
     * @return
     */
    List<ToolT> selectToolsByToolName(String toolName);
}