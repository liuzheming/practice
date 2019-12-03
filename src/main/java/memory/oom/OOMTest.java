package memory.oom;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 *
 * Created by lzm on 2019/11/26.
 */
public class OOMTest {

  private Logger log = LoggerFactory.getLogger(OOMTest.class);


  private void oomTest() {

    List<OOMTest> list = new ArrayList<>();
    while (true) {
      list.add(new OOMTest());
    }
  }


  public static void main(String... args) {
    OOMTest test = new OOMTest();
    Thread thread = new Thread(new OOMThread());
    thread.start();
    test.oomTest();
  }


}

class OOMThread implements Runnable {

  private Logger log = LoggerFactory.getLogger(OOMThread.class);

  @Override
  public void run() {
    // 干扰内存泄漏分析
    int i = 0;
    List<String[]> link = new ArrayList<>();
    while (true) {
      i++;
      link.add(new String[10]);
    }
  }
}

class SharedData {

  public List<String[]> list = new ArrayList<>();


}