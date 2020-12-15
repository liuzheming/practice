package consistent;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2020/12/15
 */
public class Main {

    public static List<Device> DEVICE_LIST = new ArrayList<>();

    public static void main(String... args) {

        DEVICE_LIST.add(new Device("111"));
        DEVICE_LIST.add(new Device("222"));
        DEVICE_LIST.add(new Device("333"));
        DEVICE_LIST.add(new Device("444"));

        ClusterNode node1 = new ClusterNode("127.0.0.1");
        node1.printDevices();
        ClusterNode node2 = new ClusterNode("127.0.0.2");
        node2.printDevices();
        ClusterNode node3 = new ClusterNode("127.0.0.3");
        node3.printDevices();

        ZK.delNode(node1);
        ZK.print();

    }

}

