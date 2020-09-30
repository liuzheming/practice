package memory.stack;

/**
 * Create by lzm on 2020/10/1
 * <p>
 * 用于理解Java如何开辟内存
 */
public class Math {

    public static final int initData = 1;

    public static User user = new User();

    public int compute() {

        // 操作数栈 压栈1
        // 局部变量 位置1：操作数栈出栈，放入局部变量表1
        int a = 1;
        // 操作数栈 压栈100
        // 局部变量 位置2：操作数栈出栈，放入局部变量表2
        int b = 100;
        // 操作数栈压栈，加载局部变量表位置1、位置2
        // 操作数栈，弹出最上面的两个元素，执行 + ，结果再次入栈
        // 操作数栈，压入10
        // 操作数栈，弹出最上面2个元素，执行 *，结果再入栈
        // 局部变量表位置3，操作数栈出栈，赋值到位置3
        int c = (a + b) * 10;

        // 操作数栈加载局部变量3
        // 操作数栈弹出结果，并返回
        return c;
    }

    public static void main(String... args) {
        Math math = new Math();

        // 动态链接：当执行到此处，通过动态链接获取compute方法指令的地址并找到要被执行的指令、方法出口
        math.compute();
        // 方法出口：compute执行完毕，线程需要知道从何处继续向下执行，方法出口作用在于提供标记
    }

}
