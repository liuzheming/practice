package thread.section1.stopThread;

/**
 * Description: 沉睡中的线程如果被打上停止标记,会抛出异常,进而停止执行try-catch
 * 内的代码
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class StopThread3 extends Thread {


    @Override
    public void run() {
        super.run();
        try {
            System.out.println("开始执行");
            Thread.sleep(5000000);
            System.out.println("执行结束");
        } catch (InterruptedException e) {
            System.out.println("沉睡中被停止...");
            e.printStackTrace();
        }
        System.out.println("lllllll");

    }
}
