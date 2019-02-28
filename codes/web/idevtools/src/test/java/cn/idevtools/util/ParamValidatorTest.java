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

    @Test
    public void emailCheckTest() {
        System.out.println(ParamValidator.isEmailValid("slkj*#@lkj.com")); // false
        System.out.println(ParamValidator.isEmailValid("lichaoxi7@qq.com")); // true
        System.out.println(ParamValidator.isEmailValid("")); // false
        System.out.println(ParamValidator.isEmailValid(null)); // false
        System.out.println(ParamValidator.isEmailValid("lk.sd.223.sd@164.coms.2n")); // true
    }
}
