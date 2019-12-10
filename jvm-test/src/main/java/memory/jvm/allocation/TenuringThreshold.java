package memory.jvm.allocation;

/**
 * Description:
 *
 * Created by lzm on 2019/12/10.
 */
public class TenuringThreshold {

  private static int _1MB = 1024 * 1024;

  public static void main(String... args) {
    testTenuringThreshold();
  }


  /**
   * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
   * -XXMaxTenuringThreshold=1 -XX:PrintTenuringDistribution
   */
  public static void testTenuringThreshold() {
    byte[] allocation1, allocation2, allocation3;
    allocation1 = new byte[_1MB / 4];
    allocation2 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
    allocation3 = null;
    allocation3 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
    allocation3 = new byte[4 * _1MB];
  }

}
