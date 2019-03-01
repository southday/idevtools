package cn.idevtools.common;

/**
 * 状态码-消息 Class
 * 编码规则：
 * <pre>
 * 1) 成功 code = 1，失败 code = -1
 * 2) 其他特殊定义的code，如：
 *      VALID_ERROR(-8, "表单校验失败")
 * </pre>
 * @author southday
 * @date 2019/2/28
 * @see CodeMsgE
 */
public class CodeMsgC {
    private int code;
    private String msg;

    public CodeMsgC(int code, String msg) {
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
