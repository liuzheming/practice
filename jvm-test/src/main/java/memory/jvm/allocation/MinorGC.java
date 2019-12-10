package memory.jvm.allocation;


/**
 * Description: 创建对象,优先在新生代分配内存,如果新生代空间不足,则将对象转入老年代
 *
 * 转入老年代的规则:
 * 1. 老年代的连续空闲区域是否足以应对新生代对象全部存活的情况
 * 2. 老年代的连续空间区域是否大于历次升级对象大小的平均值
 * 如果两条都不符合,则直接触发FullGC
 * 如果两条中有一条符合,则触发MinorGC----清理死亡的对象并把eden中存活的对象转入survivor区or老年代。
 *
 * VM Args: -verbose:gc -Xms20M  -Xmx20M -Xmn:10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * Created by lzm on 2019/12/5.
 */
public class MinorGC {

  private static final int _1MB = 1024 * 1024;


  public static void main(String... args) throws Exception {
    testAllocation();
  }


  /**
   * 测试-1
   * 新生代空间不够时,触发 MinorGC
   */
  public static void testAllocation() throws Exception {

    byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
//    byte[] allocation1;
    allocation2 = new byte[2 * _1MB];
    allocation3 = new byte[2 * _1MB];
    allocation4 = new byte[2 * _1MB];
    /*
     * 按照《深入理解JVM》介绍,此处应该触发一次minor GC,然后再将对象前三个小对象转移到OldGen,大对象创建在Eden
     * 但是GC Detail日志显示,此处没有触发GC,而是直接进入了老年代
     */
    allocation5 = new byte[4 * _1MB];

  }


  /**
   * 测试-2
   * 大小超过PretenureSizeThreshold的对象,直接进入老年代
   */
  public static void testPretenureSizeThredhold() {
    byte[] allocation = new byte[4 * _1MB];
  }




}
