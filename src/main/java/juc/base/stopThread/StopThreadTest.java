package juc.base.stopThread;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class StopThreadTest {


    public static void main(String... args) {
        test1();
    }

    private static void test() {

        Thread thread = new StopThread3();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
        thread.interrupt();

    }

    private static void test1() {
        Thread thread = new StopThread5();
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
        thread.interrupt();
    }

}
