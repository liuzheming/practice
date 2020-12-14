package juc.watchDog;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/12/25.
 */
public class ServiceThread implements Runnable {


    private String username;

    private String password;

    private Service service;

    public ServiceThread() {

    }

    public ServiceThread(Service service, String username, String password) {
        this.service = service;
        this.username = username;
        this.password = password;
    }


    @Override
    public void run() {
        service.setUsernamePassword(username, password);
    }
}
