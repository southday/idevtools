package cn.idevtools.common;

/**
 * 请求返回的状态码，使用enum来限制使用者的对其参数选择范围
 * @author southday
 * @date 2019/3/3
 */
public enum StatusCode {
    SUCCESS(1),
    FAILURE(-1),
    VALID_ERROR(-8), // 表单验证错误
    NONE(0); // 空，占位符

    private final int value;

    StatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
