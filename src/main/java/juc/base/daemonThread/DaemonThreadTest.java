package juc.base.daemonThread;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/23.
 */
public class DaemonThreadTest {


    public static void main(String... args) {


        Runnable runnable = new MyDaemonThread();

        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程退出,守护线程也将停止!");

    }

}
