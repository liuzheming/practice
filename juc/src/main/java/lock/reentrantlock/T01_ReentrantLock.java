package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Create by lzm on 2020/10/23
 */
public class T01_ReentrantLock {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }


}
