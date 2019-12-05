package memory.jvm.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 虚拟机栈和本地方法栈OOM测试
 * VM Args: -Xss128k
 * 单个线程下,不论是栈帧太大,还是虚拟机栈太小,当内存无法分配的时候,都会报出StackOverFlowError异常
 *
 * Created by lzm on 2019/12/3.
 */
@Slf4j
public class JavaVMStackSOF {


  private int stackLength = 1;

  public void stackLeak() {
    stackLength++;
    stackLeak();
  }


  public static void main(String... args) {

    JavaVMStackSOF oom = new JavaVMStackSOF();
    try {
      oom.stackLeak();
    } catch (Throwable e) {
      log.error("stack length:" + oom.stackLength, e);
    }

  }

}
