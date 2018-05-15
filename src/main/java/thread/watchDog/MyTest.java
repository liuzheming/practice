package thread.watchDog;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/25.
 */
public class MyTest {


    public static void main(String... args) {

        MyOneList list = new MyOneList();
        MyThread thread1 = new MyThread(list, "阿童木");
        MyThread thread2 = new MyThread(list, "奥特曼");
        new Thread(thread1).start();
        new Thread(thread2).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());

    }

}
