package thread.section1;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/19.
 */
public class MyThread extends Thread {


    private int count = 0;

    public MyThread() {
        super();
    }

    public MyThread(String name) {
        super(name);
    }

    public MyThread(String name, int i) {
        this(name);
        this.count = i;
    }

    @Override
    synchronized public void run() {
        run8();
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


    private void run3() {
        while (count > 0) {
            count--;
            System.out.println("Thread " + Thread.currentThread().getName() + " count=" + count);
        }
    }

    private void run4() {
        count--;
        System.out.println("Thread " + Thread.currentThread().getName() + " count " + count);
    }


    private void run5() {
        System.out.println("run : isAlive=" + this.isAlive());
        System.out.println(this.getName() + " " + this.getId());
    }

    private void run8() {
        for (int i = 0; i < 500000; i++) {
            System.out.println("i=" + i);
        }
    }


}
