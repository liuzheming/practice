package thread.base.daemonThread;

/**
 * Description: 守护线程,当所有的非守护线程都退出后,守护线程将自动退出,
 * 只要有任何的非守护线程没有结束,守护线程就不会停止
 * 守护线程最典型的案例,就是GC
 * <p>
 * Created by lzm on 2017/12/23.
 */
public class MyDaemonThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("我是守护线程......");
            System.out.println();
            Thread.yield();
            Thread.yield();
            Thread.yield();
            Thread.yield();
            Thread.yield();
            Thread.yield();
        }
    }


}
