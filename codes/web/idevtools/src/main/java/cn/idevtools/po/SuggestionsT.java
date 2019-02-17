package cn.idevtools.po;

import java.util.Date;

public class SuggestionsT {
    private Integer sugId;

    private Integer userId;

    private String email;

    private String content;

    private Date sugTime;

    public Integer getSugId() {
        return sugId;
    }

    public void setSugId(Integer sugId) {
        this.sugId = sugId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSugTime() {
        return sugTime;
    }

    public void setSugTime(Date sugTime) {
        this.sugTime = sugTime;
    }
}