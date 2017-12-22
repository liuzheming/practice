package thread.section1;

/**
 * Description:不论是Thread还是runnable形式的线程代码载体,都存在
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class MyRunnable implements Runnable {


    @Override
    public void run() {
        System.out.println("运行中!");
    }
}
