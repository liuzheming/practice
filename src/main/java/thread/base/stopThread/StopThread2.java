package thread.base.stopThread;

/**
 * Description:  循环配合异常法来停止线程,但可以停止try-catch块内的代码执行
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class StopThread2 extends Thread {


    /**
     * 测试使用标记退出线程代码的执行,抛出InterruptException,所有try-catch
     * 内的代码都会停止
     */
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100000000; i++) {
            System.out.println(i);
            if (this.isInterrupted()) {
                try {
                    throw new InterruptedException();
                } catch (InterruptedException e) {
                    System.out.println("");
                    e.printStackTrace();
                }
            }
        }
        System.out.println("我在for外");

    }
}
