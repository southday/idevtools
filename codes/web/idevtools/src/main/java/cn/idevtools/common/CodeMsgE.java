package cn.idevtools.common;

/**
 * 预定义 状态码-消息 Enum
 * @author southday
 * @date 2019/2/26
 */
public enum CodeMsgE {
    LOGIN_SUCCESS(1, "登录成功"),
    JOIN_SUCCESS(2, "注册成功"),

    CAPTCHA_ERROR(-1, "验证码错误"),
    LOGIN_FAILURE_INPUT_ERROR(-2, "登录失败，用户名或密码错误"),
    LOGIN_FAILURE_TOKEN_ERROR(-3, "登录失败，Token创建异常"),
    JOIN_FAILURE_INPUT_NULL(-4, "注册失败，参数为空"),
    JOIN_FAILURE_EMAIL_ERROR(-5, "注册失败，Email非法"),
    JOIN_FAILURE_EMAIL_EXISTS(-6, "注册失败，Email已被使用"),
    JOIN_FAILURE_USERN_EXISTS(-7, "注册失败，用户名已被使用");

    private int code;
    private String msg;

    CodeMsgE(int code, String msg) {
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
