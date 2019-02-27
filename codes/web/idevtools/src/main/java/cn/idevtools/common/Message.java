package cn.idevtools.common;

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

    public Message(CodeMsg codeMsg) {
        this(codeMsg, null);
    }

    public Message(CodeMsg codeMsg, T data) {
        this(codeMsg.getCode(), codeMsg.getMsg(), data);
    }

    public Message(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
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
