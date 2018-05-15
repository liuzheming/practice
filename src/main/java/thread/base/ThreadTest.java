package thread.base;

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
//        test5();
//        test6();
//        test7();
//        test8();
        test9();
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


    /**
     * 非线程安全的示例
     */
    public static void test5() {
        Login a = new Login("a", "aaa");
        Login b = new Login("b", "bbb");
        new Thread(a).start();
        new Thread(b).start();
    }


    /**
     * 打印当前线程示例
     */
    private static void test6() {
        CountOperate co = new CountOperate();
        Thread t1 = new Thread(co);
        t1.setName("A");
        t1.start();
    }


    private static void test7() {
        Thread thread = new MyThread();
        System.out.println("isAlive=" + thread.isAlive());
        thread.start();
        System.out.println("isAlive=" + thread.isAlive());
    }


    private static void test8() {

        Thread thread = new MyThread();

        try {
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println(Thread.interrupted());  //interrupted()方法表示查看线程是否被打断。
            System.out.println(Thread.interrupted());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }

    private static void test9() {

        Thread thread = new MyThread();

        try {
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }

}
