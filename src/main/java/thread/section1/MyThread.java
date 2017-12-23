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
        run9();
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


    /**
     * 测试使用标记来停止线程代码的执行,但是并不会停止线程
     */
    private void run8() {
        for (int i = 0; i < 500000; i++) {
            System.out.println("i=" + i);
            if (this.isInterrupted()) {
                System.out.println("已标记为被打断,退出...");
                break;
            }
        }
        System.out.println("我被输出,说明已经退出for循环,但是线程并未停止!");
    }

    /**
     * 测试使用标记退出线程代码的执行,抛出InterruptException,直接停止线程
     */
    private void run9() {
        try {
            for (int i = 0; i < 1000000; i++) {
                System.out.println(i);
                if (this.isInterrupted()) {
                    throw new InterruptedException();
                }
            }
            System.out.println("我在for循环外,如果我被打印,说明线程没有被停止!");
        } catch (InterruptedException e) {
            System.out.println("使用异常打断了线程");
        }


    }

}
