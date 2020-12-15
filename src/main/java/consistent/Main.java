package consistent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Create on 2020/12/15
 */
public class Main {

    public static List<Device> DEVICE_LIST = new ArrayList<>();

    public static void main(String... args) {

        for (int i = 0; i < 1000; i++) {
            DEVICE_LIST.add(new Device(i + "", UUID.randomUUID().toString().replaceAll("-", "")));
        }

        ClusterNode node1 = new ClusterNode("127.0.0.1");
        node1.printDevices();
        ClusterNode node2 = new ClusterNode("127.0.0.2");
        node2.printDevices();
        ClusterNode node3 = new ClusterNode("127.0.0.3");
        node3.printDevices();

        node1.unregisterFromZk();
        ZK.print();

    }

}

