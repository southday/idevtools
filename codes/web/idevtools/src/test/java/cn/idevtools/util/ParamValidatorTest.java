package cn.idevtools.util;

import org.junit.Test;

/**
 * @author southday
 * @date 2019/2/27
 */
public class ParamValidatorTest {

    @Test
    public void foo() {
        System.out.println(ValidUtil.notNullString());
        System.out.println(ValidUtil.notNullString(null));
        System.out.println(ValidUtil.notNullString(null, null, "xx"));
        System.out.println(ValidUtil.notNullString("", "", ""));
        System.out.println(ValidUtil.notNullString("aa", "a", "a"));
    }

    @Test
    public void emailCheckTest() {
        System.out.println(ValidUtil.isEmailValid("slkj*#@lkj.com")); // false
        System.out.println(ValidUtil.isEmailValid("lichaoxi7@qq.com")); // true
        System.out.println(ValidUtil.isEmailValid("")); // false
        System.out.println(ValidUtil.isEmailValid(null)); // false
        System.out.println(ValidUtil.isEmailValid("lk.sd.223.sd@164.coms.2n")); // true
    }
}
