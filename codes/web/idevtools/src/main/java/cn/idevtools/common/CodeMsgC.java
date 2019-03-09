package cn.idevtools.common;

/**
 * 状态码-消息 Class，使外部可new对象，作为预定义CodeMsgE的补充
 * 编码规则：
 * <pre>
 * 1) 成功 code = StatusCode.SUCCESS，失败 code = StatusCode.FAILURE
 * 2) 其他特殊定义的code，如：
 *      VALID_ERROR(StatusCode.VALID_ERROR, "表单校验失败")
 * </pre>
 * @author southday
 * @date 2019/2/28
 * @see CodeMsgE
 */
public class CodeMsgC {
    private StatusCode code;
    private String msg;

    public CodeMsgC(StatusCode code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
