package juc.deadLock;

/**
 * Description: 死锁测试
 * <p>
 * 死锁: 多个线程相互等待对方所持有的资源时,导致程序永远无法向下执行的情况
 * <p>
 * 解决方案: 在所有线程中,依次按照同样的顺序获取资源?
 * <p>
 * Created by lzm on 2017/9/5.
 */
public class ThreadA extends Thread {

    final private Object key1;

    final private Object key2;

    public ThreadA(Object key1, Object key2) {
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
        synchronized (key1) {
            ThreadB.sleep();
            synchronized (key2) {

            }
        }
    }

    public static void main(String... args) {

        Object key1 = new Object(), key2 = new Object();
        new ThreadA(key1, key2).start();
        new ThreadB(key1, key2).start();

    }

}
