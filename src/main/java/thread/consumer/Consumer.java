package thread.consumer;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/9/5.
 */
class Consumer implements Runnable {

    ProductBox productBox = null;

    public Consumer(ProductBox pb) {
        this.productBox = pb;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            Product p = productBox.pop();
            System.out.println("comsume : " + p);
        }
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
