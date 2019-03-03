package cn.idevtools.po;

/**
 * 一个下载记录对应一个工具
 *
 * @author 王沁宽
 * @date 2019/3/3
 */
public class DownloadToolVO {

    private DownloadsT download;

    private ToolT tool;

    public DownloadsT getDownload() {
        return download;
    }

    public void setDownload(DownloadsT download) {
        this.download = download;
    }

    public ToolT getTool() {
        return tool;
    }

    public void setTool(ToolT tool) {
        this.tool = tool;
    }
}
