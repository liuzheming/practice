package thread.synchronize;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/23.
 */
public class HasSelfPrivateNum implements Runnable {

    private int i = 0;

    private String username;

    public HasSelfPrivateNum() {

    }

    public HasSelfPrivateNum(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        addI();
    }

    public void addI() {
        addI(username);
    }

    public void addI(String username) {
        if (username == null) {
            throw new NullPointerException("username不能为空");
        }

        try {
            if (username.equals("镇元大仙")) {
                i = 100;
            } else if (username.equals("元始天尊")) {
                i = 200;
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}
