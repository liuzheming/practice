package thread.section1;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class CountOperate extends Thread {

    public CountOperate() {
        System.out.println("init CountOperate---begin");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("init CountOperate---end");
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("run---end");
    }
}
