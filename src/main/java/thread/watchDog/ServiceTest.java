package thread.watchDog;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/25.
 */
public class ServiceTest {


    public static void main(String... args) {

        Object lock = new Object();
        Service service1 = new Service(new String("aaa"));
        Service service2 = new Service(new String("aaa"));
        ServiceThread serviceThread1 = new ServiceThread(service1, "擎天柱", "123");
        ServiceThread serviceThread2 = new ServiceThread(service2, "威震天", "456");

        Thread thread1 = new Thread(serviceThread1);
        Thread thread2 = new Thread(serviceThread2);

        thread1.start();
        thread2.start();

    }


}
