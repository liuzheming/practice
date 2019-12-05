package memory.jvm.exception;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 模拟Heap内存溢出
 *
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOutOfMemoryError
 *
 * Created by lzm on 2019/11/26.
 */
public class HeapOOM {

  private Logger log = LoggerFactory.getLogger(HeapOOM.class);


  private void oomTest() {

    List<HeapOOM> list = new ArrayList<>();
    while (true) {
      list.add(new HeapOOM());
    }
  }


  public static void main(String... args) {
    HeapOOM test = new HeapOOM();
    Thread thread = new Thread(new OOMThread());
    thread.start();
    test.oomTest();
  }


  static class OOMThread implements Runnable {

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

}
