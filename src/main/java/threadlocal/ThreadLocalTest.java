package threadlocal;

/**
 * Description:
 *
 * Created by lzm on 2019/11/17.
 */
public class ThreadLocalTest {


  private static ThreadLocal tl = new ThreadLocal();


  public static void main(String... args) {

    tl.set("ssss");

  }

}
