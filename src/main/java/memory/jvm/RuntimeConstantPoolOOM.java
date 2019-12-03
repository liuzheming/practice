package memory.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * VM Args: -XX:PermSize=10M  -XX:MaxPermSize=10M
 *
 * JDK8开始没有常量池的概念,因此-XX:PermSize 和 -XX:MaxPermSize的大小没有了
 * Created by lzm on 2019/12/3.
 */
public class RuntimeConstantPoolOOM {


  public static void main(String... args) {
    isEquals();
  }

  /**
   * intern()
   * 如果常量池不存在当前对象,则在常量池记录一个当前对象的引用,指向此对象
   * 如果常量池以存在相同对象的引用,则返回已存在的引用所指向的对象
   */
  private static void isEquals() {

    // 在heap中创建一个string对象
    String str1 = new StringBuilder("计算机").append("软件").toString();
    // intern() 方法会判断常量池是否已经存在此对象,如果不存在,则会在常量池存放一个此对象的引用,并返回此引用
    // 因此,intern() 方法返回的引用所指的对象实例和str1是同一个  返回true
    System.out.println(str1.intern() == str1);

    String str2 = new StringBuilder("ja").append("va").toString();
    // 因为在str2之前,方法区中就已经存在对字符串"java"的引用了   返回false
    System.out.println(str2.intern() == str2);

  }


  private void oom() {
    // 使用List保持常量池引用,避免Full GC 回收常量池行为
    List<String> list = new ArrayList<>();
    // 10M的PermSize在integer范围内足够产生OOM了
    int i = 0;
    while (true) {
      list.add(String.valueOf(i++));
    }
  }

}
