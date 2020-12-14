package juc.deadLock;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/9/5.
 */
public class ThreadB extends Thread {

    final private Object key1;

    final private Object key2;

    public ThreadB(Object key1, Object key2) {
        this.key1 = key1;
        this.key2 = key2;
        this.setName(this.getClass().getName());
    }

    @Override
    public void run() {
        super.run();
        fun();
    }

    private void fun() {
        synchronized (key2) {
            sleep();
            synchronized (key1) {

            }
        }
    }

    static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
