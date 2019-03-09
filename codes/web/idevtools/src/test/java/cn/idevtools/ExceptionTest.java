package cn.idevtools;

/**
 * 异常处理Test
 * @author southday
 * @date 2019/3/5
 */
public class ExceptionTest {

    public static void foo() throws Exception {
        throw new NullPointerException("空指针");
    }

    public static void soo() {
        try {
            foo();
        } catch (NullPointerException e) {
            System.out.println("catch null pointer exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        soo();
    }

    // output: catch null pointer exception
}
