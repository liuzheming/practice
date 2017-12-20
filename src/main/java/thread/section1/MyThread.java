package thread.section1;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/19.
 */
public class MyThread extends Thread {


    @Override
    public void run() {
        run2();
    }

    public void run1() {
        super.run();
        System.out.println("MyThread");
    }

    public void run2() {
        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("run=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {

        }
    }


}
