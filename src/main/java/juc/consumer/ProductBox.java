package juc.consumer;

/**
 * Description: 产品Box,将由多个线程所共享
 * <p>
 * Created by lzm on 2017/9/1.
 */
class ProductBox {

    private Product[] productBox = new Product[6];

    private int index = 0;

    ProductBox() {
        super();
    }

    /**
     * 放入新增product:
     * 1、box判满;
     * 2、放入新的product
     * 3、移动index
     * 使用synchronized关键字保证这三步操作的原子性
     */
    synchronized void push(Product p) {
        while (index == productBox.length) {    // 如果box满了,则暂停生产
            try {
                /*
                 * Object#wait()方法
                 *
                 * 1、与Thread.sleep()占着CPU睡大觉不同,调用wait时线程将让出锁
                 *
                 * 2、Object#wait(long timeout) 方法通过设定等待时间来获得锁,当timeout时间结束后,
                 * 当前线程不会立即醒来,而是会继续等待至当前正在使用锁的线程结束
                 */
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 唤醒正在等待当前锁的线程,被唤醒后的线程会等待当前线程执行完毕后才能获得锁(见wait方法2条)
        this.notify();
        productBox[index++] = p;
    }

    synchronized Product pop() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        return productBox[--index];
    }

}
