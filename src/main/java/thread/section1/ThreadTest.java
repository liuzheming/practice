package thread.section1;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/19.
 */
public class ThreadTest {


    public static void main(String... args) {

        test1();

    }


    public static void test1() {
        MyThread thread = new MyThread();
        thread.start();
//        thread.start(); // 如果多次调用start()方法,会报错
        System.out.println("运行结束");
    }



}
