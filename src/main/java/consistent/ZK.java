package consistent;

import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by lzm on 2020/12/15
 */
@Slf4j
public class ZK {

    public static Map<String, ClusterNode> IP_TO_NODE = new HashMap<>();

    public static Collection<ClusterNode> addNode(ClusterNode node) {
        Collection<ClusterNode> registyNode =  IP_TO_NODE.values();
        IP_TO_NODE.put(node.ip, node);
        for (Map.Entry<String, ClusterNode> entry : IP_TO_NODE.entrySet()) {
            if (!node.ip.equals(entry.getKey())) {
                entry.getValue().onClusterAddNode(node);
            }
        }
        print();
      return registyNode;
    }

    public static void delNode(ClusterNode node) {
        IP_TO_NODE.remove(node.ip);
        for (Map.Entry<String, ClusterNode> entry : IP_TO_NODE.entrySet()) {
            if (!node.ip.equals(entry.getKey())) {
                entry.getValue().onClusterDelNode(node);
            }
        }
        print();
    }

    public static void print() {
        log.info("ZK [" + IP_TO_NODE + "]");
    }
}
