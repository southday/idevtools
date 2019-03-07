package cn.idevtools.mapper;

import cn.idevtools.po.ToolDirT;

import java.util.List;

public interface ToolDirTMapper {

    /**
     * 查询dirId目录对应的子文件(工具、目录)
     * southday 2019.03.07
     * @param dirId
     * @return
     */
    List<ToolDirT> selectSubToolDirsByDirId(Integer dirId);
}