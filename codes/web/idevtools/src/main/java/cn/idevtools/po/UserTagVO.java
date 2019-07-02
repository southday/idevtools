package cn.idevtools.po;

import java.util.List;

/**
 * 用于返回包含了标签信息的用户信息
 *
 * @author 王沁宽
 *
 * @date 2019/2/27
 */
public class UserTagVO {

    private UserT user;

    /**
     * 一个用户持有多个标签
     */
    private List<UserTagT> userTags;

    public UserT getUser() {
        return user;
    }

    public void setUser(UserT user) {
        this.user = user;
    }

    public List<UserTagT> getUserTags() {
        return userTags;
    }

    public void setUserTags(List<UserTagT> userTags) {
        this.userTags = userTags;
    }
}
