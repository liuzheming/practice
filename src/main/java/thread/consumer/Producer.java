package thread.consumer;

/**
 * Description: 生产者
 * <p>
 * Created by lzm on 2017/9/1.
 */
public class Producer implements Runnable {

    ProductBox productBox = null;

    public Producer(ProductBox pb) {
        super();
        this.productBox = pb;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            Product p = new Product(i);
            productBox.push(p);
            System.out.println("produce : " + p);
            try {
                Thread.sleep((int) (Math.random() * 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
