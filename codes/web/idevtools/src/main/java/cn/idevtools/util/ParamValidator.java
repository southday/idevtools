package cn.idevtools.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数校验器
 * @author southday
 * @date 2019/2/27
 */
public class ParamValidator {
    private static final Pattern emailPattern;

    static {
        String regx = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        emailPattern = Pattern.compile(regx);
    }

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

    /**
     * 验证邮箱是否合法
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        return notNullString(email) && emailPattern.matcher(email).matches();
    }
}
