package cn.idevtools.util;

import org.junit.Test;

/**
 * @author southday
 * @date 2019/2/27
 */
public class ParamValidatorTest {

    @Test
    public void foo() {
        System.out.println(ParamValidator.notNullString());
        System.out.println(ParamValidator.notNullString(null));
        System.out.println(ParamValidator.notNullString(null, null, "xx"));
        System.out.println(ParamValidator.notNullString("", "", ""));
        System.out.println(ParamValidator.notNullString("aa", "a", "a"));
    }
}
