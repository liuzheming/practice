package consistent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Create by lzm on 2020/12/15
 */
@Slf4j
public class ClusterCenterImpl implements ClusterCenter {

    public SortedMap<Integer, ClusterNode> hashToNode = new TreeMap<>();

    public String ip;

    public ClusterCenterImpl(String ip) {
        this.ip = ip;
    }

    @Override
    public String getNodeIP() {
        return ip;
    }

    @Override
    public void addNode(ClusterNode clusterNode) {
        if (clusterNode != null) {
            log.info("on node added,ip:{}", clusterNode.ip);
            if (!StringUtils.isEmpty(clusterNode.ip)) {
                hashToNode.put(clusterNode.hash, clusterNode);
            }
        }
    }

    @Override
    public void removeNode(ClusterNode node) {
        hashToNode.remove(node.hash);
        log.info("on node deleted,ip:{}", node);
    }

    @Override
    public int getClusterSize() {
        return hashToNode.size();
    }

    @Override
    public SortedMap<Integer, ClusterNode> getAllNodes() {
        return this.hashToNode;
    }


    @Override
    public void clean() {
        idToNode.clear();
    }


}
