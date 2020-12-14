package juc.base;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/22.
 */
public class Login implements Runnable {

    private String username;

    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        LoginServlet.doPost(username, password);
    }


}
