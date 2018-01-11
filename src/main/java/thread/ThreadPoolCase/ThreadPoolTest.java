package thread.ThreadPoolCase;

import java.util.concurrent.*;

/**
 * Description:
 * <p>
 * Created by lzm on 2018/1/11.
 */
public class ThreadPoolTest {


    public static void main(String[] args) {


        ExecutorService service = new ThreadPoolExecutor(
                5,
                10,
                100000,
                TimeUnit.NANOSECONDS,
                new ArrayBlockingQueue<>(10)
        );

        for (int i = 0; i < 20; i++)
            service.submit(() -> {
                System.out.println("开始执行任务 : " + Thread.currentThread());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println("执行完毕 : " + Thread.currentThread());
            });

        service.shutdown();
    }


}
