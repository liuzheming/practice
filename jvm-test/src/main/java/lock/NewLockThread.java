package lock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 的测试类
 * <p>
 * ReentrantLock 对于 synchronized 的优势在于:
 * <p>
 * 1. 等待可中断
 * 使用synchronized锁，等待锁的其他线程只会一直在等待，直到获取锁
 * 使用ReentrantLock锁，当占用锁的线程长时间不释放，等待锁的线程可以中断等待，去做别的事情
 * <p>
 * 2. 可设置为公平锁
 * synchronized锁释放后，等待锁的任何线程都有同等机会获取锁，即为非公平锁
 * ReentrantLock可通过构造器的参数设置为公平锁，使等待中的线程按照请求顺序获取锁，但是会增加成本
 * <p>
 * 3. 支持一个锁绑定多个条件
 * ReentrantLock可以同时绑定多个Condition对象，只需多次调用newCondition方法即可
 * synchronized中，锁对象的wait() 和 notify()、notifyAll() 方法可以实现一个隐含的条件。但如果
 * 要和多于1个的条件关联时，就只能再增加一个锁了
 * <p>
 * <p>
 * Create by lzm on 2020/10/10
 */
public class NewLockThread implements Runnable {

    private ReentrantLock lock = new ReentrantLock();

    private ConcurrentHashMap syncMap = new ConcurrentHashMap();

    @Override
    public void run() {
        lock.lock();
        lock.newCondition();
        lock.newCondition();


        try {
            System.out.println("A");
        } finally {
            lock.unlock();
        }
    }


}
