package juc.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/8/17.
 */

public class Test {


  private static Logger logger = LoggerFactory.getLogger(Test.class);

  public static void main(String... args) {
    logger.info("submit [{}]", "start");
    System.out.print("submit [{}]");

    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 30, 10L, TimeUnit.SECONDS,
        new ArrayBlockingQueue(50), new CallerRunsPolicy());

    new Thread(new AliveCounter(executor)).start();

    for (int i = 100; i < 200; i++) {
      logger.info("submit===== [{}]", i);
      FileLineCounter fileLineCounter = new FileLineCounter(i);
      executor.submit(fileLineCounter);
    }
    for (int i = 0; i < 100; i++) {
      logger.info("submit----- [{}]", i);
      FileLineCounter fileLineCounter = new FileLineCounter(i);
      executor.submit(fileLineCounter);
    }


  }


}


class AliveCounter implements Runnable {

  private static Logger logger = LoggerFactory.getLogger(AliveCounter.class);

  private ThreadPoolExecutor executor;

  public AliveCounter(ThreadPoolExecutor executor) {
    this.executor = executor;
  }


  public void run() {
    while (true) {
      int i = executor.getActiveCount();
      try {
        Thread.sleep(100L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      logger.info("aliveCount [{}]", i);
    }
  }
}


class FileLineCounter implements Callable<Integer> {

  private static Logger logger = LoggerFactory.getLogger(FileLineCounter.class);

  private int num;

  public FileLineCounter(int num) {
    this.num = num;
  }


  @Override
  public Integer call() throws Exception {
    logger.info("in thread [{}]", num);
    Thread.sleep(100);
    return 1;
  }
}