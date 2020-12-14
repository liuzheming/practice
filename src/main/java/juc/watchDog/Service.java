package juc.watchDog;

/**
 * Description: 验证两次用 new String("AAA") 获得的对象是否被视为同一监视器
 * <p>
 * Created by lzm on 2017/12/25.
 */
public class Service {


    private String username;

    private String password;

    private final Object lock;

    public Service(Object lock) {
        this.lock = lock;
    }

    public void setUsernamePassword(String username, String password) {

        try {
            synchronized (lock) {
                System.out.println("线程名称:" + Thread.currentThread().getName() + " 在"
                        + System.currentTimeMillis() + "进入同步块");
                this.username = username;
                Thread.sleep(3000);
                this.password = password;
                System.out.println("线程名称:" + Thread.currentThread().getName() + " 在"
                        + System.currentTimeMillis() + "离开同步块");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
