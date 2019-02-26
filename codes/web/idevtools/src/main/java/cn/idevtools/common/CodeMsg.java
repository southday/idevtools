package cn.idevtools.common;

/**
 * 预定义 状态码-消息
 * @author southday
 * @date 2019/2/26
 */
public enum CodeMsg {
    CAPTCHA_ERROR(-1, "验证码错误"),
    LOGIN_SUCCESS(1, "登录成功"),
    LOGIN_FAILURE_INPUT_ERROR(-2, "登录失败，用户名或密码错误"),
    LOGIN_FAILURE_TOKEN_ERROR(-3, "登录失败，Token创建异常");

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
