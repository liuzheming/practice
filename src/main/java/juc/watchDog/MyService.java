package juc.watchDog;

/**
 * Description:
 * synchronized(非this对象) 格式写法是将x作为同步块的监视器,这样可以得出
 * 以下三个结论:
 * 1)当多个线程同时执行synchronized(x){ } 时将呈现同步效果。
 * 2)当其他线程执行x对象中synchronized(this){ } 将与
 * synchronized(x){ } 同步块呈现同步效果。
 * 3)当其他线程执行x对象中synchronized的方法时,synchronized(this){ }
 * 将与同步块同步执行。
 * <p>
 * Created by lzm on 2017/12/25.
 */
public class MyService {


    /**
     * 同步块不仅需要确保自身同步执行,且需要确保与list.size()方法同步执行,因此使用方法参数 list 对象作为监视器
     */
    public MyOneList addServiceMethod(MyOneList list, String data) {
        try {
            synchronized (list) {
                if (list.size() < 1) {
                    Thread.sleep(2000);  // 模拟远程花费2秒钟取回数据
                    list.add(data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

}
