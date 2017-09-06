package thread.consumer;

/**
 * Description: 生产者
 * <p>
 * Created by lzm on 2017/9/1.
 */
class Producer implements Runnable {

    private ProductBox productBox = null;

    Producer(ProductBox pb) {
        super();
        this.productBox = pb;
    }

    /**
     * 每生产一个产品,休息若干秒
     */
    public void run() {
        for (int i = 0; i < 10; i++) {
            Product p = new Product(i);
            productBox.push(p);
            System.out.println("produce : " + p);
            try {
                /*
                 Thread.sleep() 使当前线程暂停一段时间,但是并不会释放锁
                 */
                Thread.sleep((int) (Math.random() * 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
