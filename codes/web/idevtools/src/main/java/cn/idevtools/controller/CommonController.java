package cn.idevtools.controller;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.po.ToolDirT;
import cn.idevtools.po.ToolT;
import cn.idevtools.service.CommonService;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 公共服务 Controller
 * @author southday
 * @date 2019/3/7
 */
@Controller
@RequestMapping("/c")
public class CommonController {

    @Autowired
    private CommonService commonService;

    /**
     * 获取dirId对应目录的子文件(工具、目录)
     * southday 2019.03.07
     * @param dirId
     * @return
     */
    @ResponseJSONP
    @GetMapping("/view/toolDirs/sub/{dirId}")
    public Message<?> getSubToolDirs(@PathVariable Integer dirId) {
        List<ToolDirT> toolDirs = commonService.getSubToolDirs(dirId);
        return (toolDirs == null || toolDirs.size() == 0) ?
                new Message<>(CodeMsgE.QUERY_FAILURE) :
                new Message<>(CodeMsgE.QUERY_SUCCESS, toolDirs);
    }

    /**
     * 根据toolId获取指定工具信息
     * southday 2019.03.07
     * @param toolId
     * @return
     */
    @ResponseJSONP
    @GetMapping("/tools/{toolId}")
    public Message<?> getTool(@PathVariable Integer toolId) {
        ToolT tool = commonService.getToolByToolId(toolId);
        return tool == null ?
                new Message<>(CodeMsgE.QUERY_FAILURE) :
                new Message<>(CodeMsgE.QUERY_SUCCESS, tool);
    }

    /**
     * 根据关键字wd来查询工具信息
     * southday 2019.03.12
     * @param wd
     * @return
     */
    @ResponseJSONP
    @GetMapping("/search/search")
    public Message<?> searchTools(@RequestParam("wd") String wd) {
        List<ToolT> tools = commonService.searchToolsByToolName(wd);
        return tools == null || tools.size() == 0 ?
                new Message<>(CodeMsgE.QUERY_FAILURE) :
                new Message<>(CodeMsgE.QUERY_SUCCESS, tools);
    }
}
