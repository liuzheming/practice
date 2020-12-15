package consistent;

/**
 * Create by lzm on 2020/12/15
 */
public interface ClusterCenter {

    void addNode(ClusterNode clusterNode);

    void removeNode(String uuid);

    int getClusterSize();

}
