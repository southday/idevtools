package cn.idevtools.po;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class RecommendationsT {
    private Integer recId;

    private Integer userId;

    @NotEmpty(message = "工具名称不能为空")
    private String toolName;

    @NotEmpty(message = "官网地址不能为空")
    private String website;

    @NotEmpty(message = "推荐理由不能为空")
    private String reason;

    private Date recTime;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName == null ? null : toolName.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }
}