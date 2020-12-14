package threadlocal;

import java.lang.ref.WeakReference;

/**
 * Description:
 * <p>
 * 1. ThreadLocal用于存放线程本地变量
 * 2. 放入ThreadLocal中的变量，一旦使用完毕，需要立刻remove，否则容易造成内存泄露 (与WeekReference有关)
 * 3. 典型的应用案例是Spring中 @Transaction事务管理 的实现
 * <p>
 * Created by lzm on 2019/11/17.
 */
public class ThreadLocalTest {


    private static ThreadLocal tl = new ThreadLocal();


    public static void main(String... args) {

        WeakReference<String> weak = new WeakReference<>("new String()");


        tl.set("ssss");
        tl.get();
        tl.remove();
    }

}
