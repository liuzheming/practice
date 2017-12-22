package thread.section1;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/19.
 */
public class ThreadTest {


    public static void main(String... args) {
//        test1();
//        test2();
//        test3();
//        test4();

        Login a = new Login("a", "aaa");
        Login b = new Login("b", "bbb");
        new Thread(a).start();
        new Thread(b).start();

    }


    public static void test1() {
        MyThread thread = new MyThread();
        thread.setName("myThread");
        thread.start();
        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("main=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.start(); // 如果多次调用start()方法,会报错
        System.out.println("运行结束");
    }

    private static void test2() {

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        Thread thread2 = new Thread(myRunnable);
        thread.start();
        thread2.start();
        System.out.println("运行结束!");
    }

    /**
     * 多个线程间数据不共享的例子
     */
    private static void test3() {

        Thread a = new MyThread("A", 5);
        Thread b = new MyThread("B", 6);
        Thread c = new MyThread("C", 4);

        a.start();
        b.start();
        c.start();

    }


    private static void test4() {

        MyThread myThread = new MyThread("A", 20);

        Thread a = new Thread(myThread);
        Thread b = new Thread(myThread);
        Thread c = new Thread(myThread);
        Thread d = new Thread(myThread);
        Thread e = new Thread(myThread);
        Thread f = new Thread(myThread);
        Thread g = new Thread(myThread);
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        f.start();
        g.start();

    }

}
