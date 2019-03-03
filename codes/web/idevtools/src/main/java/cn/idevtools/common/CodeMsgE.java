package cn.idevtools.common;

/**
 * 预定义 状态码-消息 Enum (存放需复用的CodeMsg) <br>
 * 编码规则：
 * <pre>
 * 1) 成功 code = StatusCode.SUCCESS，失败 code = StatusCode.FAILURE
 * 2) 其他特殊定义的code，如：
 *      VALID_ERROR(StatusCode.VALID_ERROR, "表单校验失败")
 * </pre>
 * @author southday
 * @date 2019/2/26
 */
public enum CodeMsgE {
    LOGIN_SUCCESS(StatusCode.SUCCESS, "登录成功"),
    CAPTCHA_ERROR(StatusCode.FAILURE, "验证码错误"),
    LOGIN_FAILURE_INPUT_ERROR(StatusCode.FAILURE, "登录失败，用户名或密码错误"),
    LOGIN_FAILURE_TOKEN_ERROR(StatusCode.FAILURE, "登录失败，Token创建异常"),
    VALID_ERROR(StatusCode.VALID_ERROR,"表单校验失败"),
    QUERY_SUCCESS(StatusCode.SUCCESS, "查询成功"),
    INSERT_SUCCESS(StatusCode.SUCCESS, "插入成功"),
    DELETE_SUCCESS(StatusCode.SUCCESS, "删除成功"),
    UPDATE_SUCCESS(StatusCode.SUCCESS, "更新成功"),
    INSERT_FAILURE(StatusCode.FAILURE, "插入失败"),
    DELETE_FAILURE(StatusCode.FAILURE, "删除失败"),
    UPDATE_FAILURE(StatusCode.FAILURE, "更新失败"),
    LOGOUT_SUCCESS(StatusCode.SUCCESS, "退出登录成功"),
    LOGOUT_ERROR(StatusCode.FAILURE, "退出登录异常");

    private final StatusCode code;
    private final String msg;

    CodeMsgE(StatusCode code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public StatusCode getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
