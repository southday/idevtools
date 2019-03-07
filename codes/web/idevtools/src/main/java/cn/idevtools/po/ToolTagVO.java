package cn.idevtools.po;

import java.util.List;

/**
 * 一个工具 多个标签
 *
 * @author 王沁宽
 * @date 2019/3/7
 */
public class ToolTagVO {
    private ToolT tool;
    private List<ToolTagT> toolTags;

    public ToolT getTool() {
        return tool;
    }

    public void setTool(ToolT tool) {
        this.tool = tool;
    }

    public List<ToolTagT> getToolTags() {
        return toolTags;
    }

    public void setToolTags(List<ToolTagT> toolTags) {
        this.toolTags = toolTags;
    }
}
