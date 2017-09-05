package thread.consumer;

import org.junit.Test;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/9/5.
 */
public class ThreadTest {


    /**
     * jUnit本身不支持多线程执行,因为它在自身的线程执行完毕只有,使用System.exit()终止JVM,退出用例执行。
     * 因此如果需要使用jUnit来支持多线程测试,需要让主线程等待子线程执行完毕再终止。
     * <p>
     * TODO 抽空再尝试使用jUnit测试多线程的解决方案
     */
    public static void main(String... args) {

        ProductBox pb = new ProductBox();

        Producer producer = new Producer(pb);
        Consumer consumer = new Consumer(pb);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.setPriority(Thread.MAX_PRIORITY);

        producerThread.start();
        consumerThread.start();

    }

}
