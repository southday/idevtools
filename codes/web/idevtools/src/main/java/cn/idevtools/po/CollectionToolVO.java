package cn.idevtools.po;

/**
 * 一个收藏记录对应一个tool
 * 
 * @author 王沁宽
 * @date 2019/3/3
 */
public class CollectionToolVO {

    private CollectionsT collection;

    private ToolT tool;

    public CollectionsT getCollection() {
        return collection;
    }

    public void setCollection(CollectionsT collection) {
        this.collection = collection;
    }

    public ToolT getTool() {
        return tool;
    }

    public void setTool(ToolT tool) {
        this.tool = tool;
    }

}
