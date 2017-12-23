package thread.synchronize;

/**
 * Description: 给新线程的任务入参的方法要靠成员变量,因为成员变量在
 * 多个线程之间是相互可见的,而方法参数是局部变量,局部变量在只能在当前
 * 线程内部可见。
 * <p>
 * <p>
 * Created by lzm on 2017/12/23.
 */
public class SynchronizedTest {

    public static void main(String... args) {
        HasSelfPrivateNum num = new HasSelfPrivateNum();
        Thread threadA = new Thread(new ThreadA(num, "太上老君"));
        Thread threadB = new Thread(new ThreadB(num));

        threadA.start();
        threadB.start();


    }

}
