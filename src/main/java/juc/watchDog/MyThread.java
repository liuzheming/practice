package juc.watchDog;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/25.
 */
public class MyThread implements Runnable {

    private MyOneList list;

    private String data;

    public MyThread(MyOneList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() {
        MyService service = new MyService();
        service.addServiceMethod(list, data);
    }
}
