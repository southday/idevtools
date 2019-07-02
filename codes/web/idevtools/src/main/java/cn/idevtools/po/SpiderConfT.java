package cn.idevtools.po;

/**
 * 爬虫配置信息
 *
 * @author 王沁宽
 * @date 2019/5/27
 */
public class SpiderConfT {

    private Integer id;

    private String url;

    private String name;

    private String rules;

    private String setting;

    private String officalWebsiteXpath;

    private String toolNameXpath;

    private String descriptionXpath;

    private String tagXpath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getOfficalWebsiteXpath() {
        return officalWebsiteXpath;
    }

    public void setOfficalWebsiteXpath(String officalWebsiteXpath) {
        this.officalWebsiteXpath = officalWebsiteXpath;
    }

    public String getToolNameXpath() {
        return toolNameXpath;
    }

    public void setToolNameXpath(String toolNameXpath) {
        this.toolNameXpath = toolNameXpath;
    }

    public String getDescriptionXpath() {
        return descriptionXpath;
    }

    public void setDescriptionXpath(String descriptionXpath) {
        this.descriptionXpath = descriptionXpath;
    }

    public String getTagXpath() {
        return tagXpath;
    }

    public void setTagXpath(String tagXpath) {
        this.tagXpath = tagXpath;
    }


    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
