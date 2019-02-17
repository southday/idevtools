package cn.idevtools.po;

public class ToolTagT {
    private Integer tagId;

    private Integer parentTagId;

    private String tagCode;

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