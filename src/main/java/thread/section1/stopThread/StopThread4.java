package thread.section1.stopThread;

/**
 * Description: 当已经被打上停止标记的线程,进入睡眠状态,会抛出异常,进而停止执行
 * try-catch块内的代码
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class StopThread4 extends Thread {

    @Override
    public void run() {
        super.run();

        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
        }
        try {
            System.out.println("睡眠前");
            Thread.sleep(2000);
            System.out.println("睡眠后");
        } catch (InterruptedException e) {
            System.out.println("先打上停止标记,然后遇到sleep!进入catch");
            e.printStackTrace();
        }

    }
}
