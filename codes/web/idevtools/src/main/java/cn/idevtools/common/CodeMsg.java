package cn.idevtools.common;
import cn.idevtools.po.*;
/**
 * 预定义 状态码-消息
 * @author southday
 * @date 2019/2/26
 */
public enum CodeMsg {
    LOGIN_SUCCESS(1, "登录成功"),
    CAPTCHA_ERROR(-1, "验证码错误"),
    LOGIN_FAILURE_INPUT_ERROR(-2, "登录失败，用户名或密码错误"),
    LOGIN_FAILURE_TOKEN_ERROR(-3, "登录失败，Token创建异常"),

    VALID_ERROR(-4,"表单校验失败"),

    QUERY_SUCCESS(20,"查询成功"),

    INSERT_SUCCESS(21,"插入成功"),
    DELETE_SUCCESS(22,"删除成功"),
    UPDATE_SUCCESS(23,"更新成功"),
    INSERT_FAILURE(-21,"插入失败"),
    DELETE_FAILURE(-22,"删除失败"),
    UPDATE_FAILURE(-23,"更新失败");

    private int code;
    private String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
