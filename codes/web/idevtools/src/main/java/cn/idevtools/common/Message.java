package cn.idevtools.common;

import org.aspectj.apache.bcel.classfile.Code;

/**
 * 将后端返回的消息统一化
 * <pre>
 * {
 *   "code":"xxx",
 *   "msg":"xxx",
 *   "data":"xxx"
 * }
 * </pre>
 * @author southday
 * @date 2019/2/26
 */
public class Message<T> {
    private int code;
    private String msg;
    private T data;

    public Message() {
        this(0, null, null);
    }

    public Message(CodeMsgE codeMsgE) {
        this(codeMsgE, null);
    }

    public Message(CodeMsgE codeMsgE, T data) {
        this(codeMsgE.getCode(), codeMsgE.getMsg(), data);
    }

    public Message(CodeMsgC codeMsgC) {
        this(codeMsgC, null);
    }

    public Message(CodeMsgC codeMsgC, T data) {
        this(codeMsgC.getCode(), codeMsgC.getMsg(), data);
    }

    public Message(int code, String msg) {
        this(code, msg, null);
    }

    public Message(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setCodeMsg(CodeMsgE codeMsgE) {
        this.code = codeMsgE.getCode();
        this.msg = codeMsgE.getMsg();
    }

    public void setCodeMsg(CodeMsgC codeMsgC) {
        this.code = codeMsgC.getCode();
        this.msg = codeMsgC.getMsg();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
