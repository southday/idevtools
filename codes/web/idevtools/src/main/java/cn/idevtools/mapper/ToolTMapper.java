package cn.idevtools.mapper;

import cn.idevtools.po.ToolT;

public interface ToolTMapper {

    /**
     * 根据toolId查询指定工具
     * southday 2019.03.07
     * @param toolId
     * @return
     */
    ToolT selectToolByToolId(Integer toolId);
}