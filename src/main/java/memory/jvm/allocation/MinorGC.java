package memory.jvm.allocation;

/**
 * Description: 创建对象,优先在新生代分配内存,如果新生代空间不足,则将对象转入老年代
 *
 * 转入老年代的规则:
 * 1. 老年代的连续空闲区域是否足以应对新生代对象全部存活的情况
 * 2. 老年代的连续空间区域是否大于历次升级对象大小的平均值
 * 如果两条都不符合,则直接出发FullGC
 * 如果两条中有一条符合,则出发MinorGC,并将对象向老年代升级
 *
 * VM Args: -verbose:gc -Xms20M  -Xmx20M -Xmn:10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * Created by lzm on 2019/12/5.
 */
public class MinorGC {

  private static final int _1MB = 1024 * 1024;


  public static void main(String... args) {
    testAllocation();
  }


  public static void testAllocation() {

    byte[] allocation1, allocation2, allocation3, allocation4;
    allocation1 = new byte[2 * 1024];
    allocation2 = new byte[2 * 1024];
    allocation3 = new byte[2 * 1024];
    allocation4 = new byte[4 * 1024];  // 出现一次MinorGC


  }


}
