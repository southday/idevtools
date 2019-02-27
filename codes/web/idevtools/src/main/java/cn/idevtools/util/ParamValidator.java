package cn.idevtools.util;

/**
 * 参数校验器
 * @author southday
 * @date 2019/2/27
 */
public class ParamValidator {

    /**
     * 传入的参数是否所有都为非空字符串<br>
     * 非空字符串：!= null && 非空白字符串
     * @param params
     * @return 所有的字符串均为非空字符串时，返回true，否则返回false
     */
    public static boolean notNullString(String... params) {
        if (params == null || params.length == 0)
            return false;
        for (String p : params)
            if (p == null || "".equals(p.trim()))
                return false;
        return true;
    }
}
