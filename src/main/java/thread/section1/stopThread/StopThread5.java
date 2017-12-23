package thread.section1.stopThread;

/**
 * Description: interrupt() 配合 return 停止执行线程
 *
 * 除了stop方法之外的这五种方法,都只是停止代码执行,并不能停止线程
 *
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class StopThread5 extends Thread {

    @Override
    public void run() {
        super.run();

        while (true) {
            if (this.isInterrupted()) return;
            System.out.println("=====");
        }

    }
}
