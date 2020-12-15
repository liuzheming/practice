package consistent;

import java.util.SortedMap;

/**
 * Create by lzm on 2020/12/15
 */
public interface ClusterCenter {

    String getNodeIP();

    void addNode(ClusterNode clusterNode);

    void removeNode(ClusterNode node);

    int getClusterSize();

    SortedMap<Integer, ClusterNode> getAllNodes();

    void clean();

}
