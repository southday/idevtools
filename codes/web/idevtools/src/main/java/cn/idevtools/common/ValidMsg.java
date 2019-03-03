package cn.idevtools.common;

/**
 * 表单验证信息
 * 只在表单参数校验不通过的情况下返回
 *
 * @author 王沁宽
 * @date 2019/2/27
 */
public class ValidMsg {

    private String field;

    private String errorMsg;

    /**
     * 6
     * @param field 字段名
     * @param errorMsg 错误信息
     */
    public ValidMsg(String field,String errorMsg){
        this.field = field;
        this.errorMsg = errorMsg;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
