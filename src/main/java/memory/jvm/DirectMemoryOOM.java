package memory.jvm;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * Description:
 *
 * VM Args:
 * -Xmx20M
 * -XX:MaxDirectMemorySize=10M  如果不指定-XX:MaxDirectMemorySize大小,则默认和Java堆最大值(-Xmx)一样
 *
 * DirectMemory导致的内存溢出,一个明显的特点是,在Heap Dump文件中不会看见明显的异常,如果发现OOM之后dump文件
 * 很小,而程序中又直接使用了NIO,那就可以考虑检查下是不是DirectMemory方面的溢出
 *
 * 常规应用程序进行IO时,存在一个内核态与用户态相互转化的过程,相对应的就是直接内存和非直接内存,直接内存是由OS进行
 * 管理的内存,非直接内存就是由OS和应用程序共同管理的内存,垃圾收集器不会回收'直接内存'这部分。
 * 例: 当应用程序需要向客户端传输一个文件时,需要使用OS的直接内存将文件拷贝到应用程序的非直接内存(即Heap),
 * 然后再输出到直接内存,由OS发出去。
 *
 *
 * Created by lzm on 2019/12/3.
 */
public class DirectMemoryOOM {

  private static final int _1MB = 1024 * 10240;


  public static void main(String... args) throws Exception {
    Field unsafeField = Unsafe.class.getDeclaredFields()[0];
    unsafeField.setAccessible(true);
    Unsafe unsafe = (Unsafe) unsafeField.get(null);
    while (true) {
      unsafe.allocateMemory(_1MB);
    }
  }


}
