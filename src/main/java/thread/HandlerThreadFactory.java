package thread;

import java.util.concurrent.ThreadFactory;


/**
 * 异常测试
 */
public class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
//        System.out.println(1);
        return t;
    }

}


class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("================================================================================");
        System.out.println("I caught: " + e);
        System.out.println("================================================================================");
    }
}