package lock.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Create by lzm on 2020/10/23
 * <p>
 * 读写锁解决的问题是，在读多写少的条件下，增加一款比独占锁性能更高的锁
 * <p>
 * 读读：不上锁
 * 读写：上锁
 * 写写：上锁
 */
public class ReadWriteLockTest {


    public static void main(String[] args) {

        ReadWriteLockDemo demo = new ReadWriteLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.get();
            }
        }, "Read:").start();

        for (int i = 0; i < 100; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.set(2222);
                }
            }, "Write:").start();
        }
    }


}


class ReadWriteLockDemo {

    private int number = 0;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public int get() {
        lock.readLock().lock();
        try {
            System.out.println(number);
            return number;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(int x) {
        lock.writeLock().lock();
        try {
            this.number = x;
        } finally {
            lock.writeLock().unlock();
        }
    }

}
