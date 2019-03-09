package cn.idevtools.po;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserTagT {
    private Integer tagId;

    private Integer parentTagId;

    @NotNull(message = "tagCode不可为空")
    private String tagCode;

    @Size(min = 3,max = 20,message = "tagMeaning长度为3~20")
    private String tagMeaning;

    private String description;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getParentTagId() {
        return parentTagId;
    }

    public void setParentTagId(Integer parentTagId) {
        this.parentTagId = parentTagId;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode == null ? null : tagCode.trim();
    }

    public String getTagMeaning() {
        return tagMeaning;
    }

    public void setTagMeaning(String tagMeaning) {
        this.tagMeaning = tagMeaning == null ? null : tagMeaning.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}