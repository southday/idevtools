package cn.idevtools.common;

/**
 * 预定义 状态码-消息 Enum (存放需复用的CodeMsg) <br>
 * 编码规则：
 * <pre>
 * 1) 成功 code = 1，失败 code = -1
 * 2) 其他特殊定义的code，如：
 *      VALID_ERROR(-8, "表单校验失败")
 * </pre>
 * @author southday
 * @date 2019/2/26
 */
public enum CodeMsgE {
    LOGIN_SUCCESS(1, "登录成功"),
    CAPTCHA_ERROR(-1, "验证码错误"),
    LOGIN_FAILURE_INPUT_ERROR(-1, "登录失败，用户名或密码错误"),
    LOGIN_FAILURE_TOKEN_ERROR(-1, "登录失败，Token创建异常"),
    VALID_ERROR(-8,"表单校验失败"),
    QUERY_SUCCESS(1,"查询成功"),
    INSERT_SUCCESS(1,"插入成功"),
    DELETE_SUCCESS(1,"删除成功"),
    UPDATE_SUCCESS(1,"更新成功"),
    INSERT_FAILURE(-1,"插入失败"),
    DELETE_FAILURE(-1,"删除失败"),
    UPDATE_FAILURE(-1,"更新失败");

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
