package memory.stack;

/**
 * Create by lzm on 2020/10/1
 *
 * 用于理解Java如何开辟内存
 *
 */
public class Math {

    public static final int initData = 1;

    public static User user = new User();

    public int compute() {
        int a = 1;
        int b = 100;
        int c = (a + b) * 10;
        return c;
    }

}
