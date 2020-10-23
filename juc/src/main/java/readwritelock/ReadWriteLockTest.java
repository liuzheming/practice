package readwritelock;

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

    }


}


class ReadWriteLockDemo {

    private int number = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public int get() {
        lock.readLock().lock();
        try {
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
