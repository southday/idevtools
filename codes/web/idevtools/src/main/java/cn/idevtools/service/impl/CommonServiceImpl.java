package cn.idevtools.service.impl;

import cn.idevtools.mapper.ToolDirTMapper;
import cn.idevtools.mapper.ToolTMapper;
import cn.idevtools.po.ToolDirT;
import cn.idevtools.po.ToolT;
import cn.idevtools.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共服务 Service，处理一些公共的请求
 * @author southday
 * @date 2019/3/7
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private ToolDirTMapper toolDirTMapper;

    @Override
    public List<ToolDirT> getSubToolDirs(Integer dirId) {
        return toolDirTMapper.selectSubToolDirsByDirId(dirId);
    }

    @Autowired
    private ToolTMapper toolTMapper;

    @Override
    public ToolT getToolByToolId(Integer toolId) {
        return toolTMapper.selectToolByToolId(toolId);
    }

    @Override
    public List<ToolT> searchToolsByToolName(String toolName) {
        return toolTMapper.selectToolsByToolName(toolName);
    }
}
