package juc.synchronize;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/23.
 */
public class ThreadB extends Thread {

    private HasSelfPrivateNum num;

    public ThreadB(HasSelfPrivateNum num){
        this.num = num;
    }


    @Override
    public void run() {
        super.run();
        num.addI("元始天尊");

    }
}
