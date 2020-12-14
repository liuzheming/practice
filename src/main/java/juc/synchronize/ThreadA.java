package juc.synchronize;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/23.
 */
public class ThreadA implements Runnable {

    private HasSelfPrivateNum num;

    private String username;

    public ThreadA(HasSelfPrivateNum num, String username) {
        this(num);
        this.username = username;
    }

    public ThreadA(HasSelfPrivateNum num) {
        this.num = num;
        this.username = "镇元大仙";
    }

    @Override
    public void run() {
        num.addI(username);
    }
}
