package cn.idevtools.service;

import cn.idevtools.po.ToolDirT;
import cn.idevtools.po.ToolT;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共服务 Service，处理一些公共的请求
 * @author southday
 * @date 2019/3/7
 */
@Service
public interface CommonService {

    /**
     * 获取指定dirId对应的子文件信息(工具、目录)
     * southday 2019.03.07
     * @param dirId
     * @return
     */
    List<ToolDirT> getSubToolDirs(Integer dirId);

    /**
     * 通过toolId获取对应的工具信息
     * southday 2019.03.07
     * @param toolId
     * @return
     */
    ToolT getToolByToolId(Integer toolId);

    /**
     * 根据toolName来查询工具信息
     * southday 2019.03.12
     * @param toolName
     * @return
     */
    List<ToolT> searchToolsByToolName(String toolName);

    /**
     * 根据dirName查询工具目录信息
     * southday 2019.05.13
     * @param dirName
     * @return
     */
    List<ToolDirT> searchToolByDirName(String dirName);
}
