package juc.base.stopThread;

/**
 * Description: 用循环来配合break来停止线程,但是只能停止执行循环内的代码
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class StopThread1 extends Thread {

    /**
     * 测试使用标记来停止线程代码的执行,但是并不会停止线程
     */
    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            System.out.println("i=" + i);
            if (this.isInterrupted()) {
                System.out.println("已标记为被打断,退出...");
                break;
            }
        }
        System.out.println("我被输出,说明已经退出for循环,但是线程并未停止!");
    }
}
