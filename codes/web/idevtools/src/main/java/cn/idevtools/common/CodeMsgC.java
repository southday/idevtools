package cn.idevtools.common;

/**
 * 状态码-消息 Class
 * @author southday
 * @date 2019/2/28
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
