package memory.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 模拟因线程过多造成虚拟机内存溢出
 *
 *
 * **********************************
 * ***注意本测试会导致电脑直接死机重启***
 * **********************************
 *
 *
 * VM Args: -Xss2M
 * Created by lzm on 2019/12/3.
 */
@Slf4j
public class JavaVMStackOOM {

  int i;

  private void dontStop(int i) {
    while (true) {
//      log.info("我是线程[{}]", i);
    }
  }

  public void stackLeakByThread() {
    while (true) {
      log.info("准备创建第[{}]个线程", i++);
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          dontStop(i);
        }
      });
      thread.start();
    }
  }

  public static void main(String... args) {
    JavaVMStackOOM oom = new JavaVMStackOOM();
    oom.stackLeakByThread();
  }


}
